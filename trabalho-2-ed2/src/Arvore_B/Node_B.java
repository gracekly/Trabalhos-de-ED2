package Arvore_B;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node_B<T extends Comparable<T>> implements Serializable {
    private static final long serialVersionUID = 1L;

    int t; // Grau mínimo
    List<T> keys;
    List<Node_B<T>> children;
    boolean isLeaf;
    int numKeys;

    public Node_B(int t, boolean isLeaf) {
        this.t = t;
        this.isLeaf = isLeaf;
        this.keys = new ArrayList<>(2 * t - 1);
        this.children = new ArrayList<>(2 * t);
        this.numKeys = 0;
    }

    //Inserir nao Cheio, caso mais simples
    public void insertNonFull(T key) {
        int i = numKeys - 1;

        if (isLeaf) {
            while (i >= 0 && keys.get(i).compareTo(key) > 0) {
                i--;
            }
            keys.add(i + 1, key);
            numKeys++;
        } else {
            while (i >= 0 && keys.get(i).compareTo(key) > 0) {
                i--;
            }
            i++;
            if (children.get(i).numKeys == 2 * t - 1) {
                splitChild(i, children.get(i));
                if (keys.get(i).compareTo(key) < 0) {
                    i++;
                }
            }
            children.get(i).insertNonFull(key);
        }
    }

    //Quando o no estiver cheio, devemos fazer o Split, para so depois gerarmos um no nao cheio e usarmos a função de inserir normalmente
    public void splitChild(int i, Node_B<T> y) {
        Node_B<T> z = new Node_B<>(y.t, y.isLeaf);
        z.numKeys = t - 1;

        for (int j = 0; j < t - 1; j++) {
            z.keys.add(y.keys.get(j + t));
        }

        if (!y.isLeaf) {
            for (int j = 0; j < t; j++) {
                z.children.add(y.children.get(j + t));
            }
        }

        y.numKeys = t - 1;

        children.add(i + 1, z);
        keys.add(i, y.keys.get(t - 1));
        numKeys++;
    }


    //Função de imprimir um Nó, com seu respectivo nível, para visualizarmos os níveis em cada nó
    public void printNode(int level) {
        System.out.print("Level " + level + " [");
        for (int i = 0; i < numKeys; i++) {
            System.out.print(keys.get(i));
            if (i < numKeys - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        if (!isLeaf) {
            for (int i = 0; i <= numKeys; i++) {
                children.get(i).printNode(level + 1);
            }
        }
    }

    //Função de Busca em Um Nó
    public T search(T key) {
        int i = 0;
        while (i < numKeys && key.compareTo(keys.get(i)) > 0) {
            i++;
        }

        if (i < numKeys && keys.get(i).compareTo(key) == 0) {
            return keys.get(i);
        }


        return isLeaf ? null : children.get(i).search(key);
    }


    //Função de Remover.
    //Aqui queremos saber se vamos resovler de uma folha ou não
    //E avaliar os casos em que a remoção deixa o Nó com um número menor do que o mínimo permitido para a ordem da Arvore
    public void remove(T key) {
        int idx = findKey(key);

        if (idx < numKeys && keys.get(idx).compareTo(key) == 0) {
            if (isLeaf) {
                removeFromLeaf(idx);
            } else {
                removeFromNonLeaf(idx);
            }
        } else {
            if (isLeaf) {
                return;
            }

            boolean flag = (idx == numKeys);

            if (children.get(idx).numKeys < t) {
                fill(idx);
            }

            if (flag && idx > numKeys) {
                children.get(idx - 1).remove(key);
            } else {
                children.get(idx).remove(key);
            }
        }
    }

    //Utilizada para
    private int findKey(T key) {
        int idx = 0;
        while (idx < numKeys && keys.get(idx).compareTo(key) < 0) {
            idx++;
        }
        return idx;
    }

    //Função para Remover da Folha, bem simples.
    private void removeFromLeaf(int idx) {
        for (int i = idx + 1; i < numKeys; ++i) {
            keys.set(i - 1, keys.get(i));
        }
        numKeys--;
    }

    //Quando Vamos remover de uma não folha, temos casos a tratar
    //
    private void removeFromNonLeaf(int idx) {
        T key = keys.get(idx);

        if (children.get(idx).numKeys >= t) {
            T pred = getPred(idx);
            keys.set(idx, pred);
            children.get(idx).remove(pred);
        } else if (children.get(idx + 1).numKeys >= t) {
            T succ = getSucc(idx);
            keys.set(idx, succ);
            children.get(idx + 1).remove(succ);
        } else {
            merge(idx);
            children.get(idx).remove(key);
        }
    }


    //Pegar o predecessor
    private T getPred(int idx) {
        Node_B<T> cur = children.get(idx);
        while (!cur.isLeaf) {
            cur = cur.children.get(cur.numKeys);
        }
        return cur.keys.get(cur.numKeys - 1);
    }

    //pegar o Sucessor
    private T getSucc(int idx) {
        Node_B<T> cur = children.get(idx + 1);
        while (!cur.isLeaf) {
            cur = cur.children.get(0);
        }
        return cur.keys.get(0);
    }

    //Aqui juntamos todos os casos quando retirar a chave do Nó deixa ele com menos do que o minimo
    //Tentamos pegar empretado com o irmão da esquerda, e da direita e ao final, se n der nenhum. Só por fim fazemos o Merge
    private void fill(int idx) {
        if (idx != 0 && children.get(idx - 1).numKeys >= t) {
            EmprestadoPrev(idx);
        } else if (idx != numKeys && children.get(idx + 1).numKeys >= t) {
            EmprestadoNext(idx);
        } else {
            if (idx != numKeys) {
                merge(idx);
            } else {
                merge(idx - 1);
            }
        }
    }

    //Quando conseguimos pegar empretado do irmao à Esquerda
    private void EmprestadoPrev(int idx) {
        Node_B<T> child = children.get(idx);
        Node_B<T> sibling = children.get(idx - 1);

        for (int i = child.numKeys - 1; i >= 0; --i) {
            child.keys.set(i + 1, child.keys.get(i));
        }

        if (!child.isLeaf) {
            for (int i = child.numKeys; i >= 0; --i) {
                child.children.set(i + 1, child.children.get(i));
            }
        }

        child.keys.set(0, keys.get(idx - 1));

        if (!child.isLeaf) {
            child.children.set(0, sibling.children.get(sibling.numKeys));
        }

        keys.set(idx - 1, sibling.keys.get(sibling.numKeys - 1));

        child.numKeys += 1;
        sibling.numKeys -= 1;
    }

    //Quando conseguimos pegar empretado do irmao à Direita

    private void EmprestadoNext(int idx) {
        Node_B<T> child = children.get(idx);
        Node_B<T> sibling = children.get(idx + 1);

        child.keys.set(child.numKeys, keys.get(idx));

        if (!child.isLeaf) {
            child.children.set(child.numKeys + 1, sibling.children.get(0));
        }

        keys.set(idx, sibling.keys.get(0));

        for (int i = 1; i < sibling.numKeys; ++i) {
            sibling.keys.set(i - 1, sibling.keys.get(i));
        }

        if (!sibling.isLeaf) {
            for (int i = 1; i <= sibling.numKeys; ++i) {
                sibling.children.set(i - 1, sibling.children.get(i));
            }
        }

        child.numKeys += 1;
        sibling.numKeys -= 1;
    }


    //Necessaaria na Remoção quando o Nó de onde queremos remover
    //Vai ficar co quantidade menor do que o minimo e além disso, não conseguimos pegar "empretado com seus irmãos"
    private void merge(int idx) {
        Node_B<T> child = children.get(idx);
        Node_B<T> sibling = children.get(idx + 1);

        child.keys.set(t - 1, keys.get(idx));

        for (int i = 0; i < sibling.numKeys; ++i) {
            child.keys.set(i + t, sibling.keys.get(i));
        }

        if (!child.isLeaf) {
            for (int i = 0; i <= sibling.numKeys; ++i) {
                child.children.set(i + t, sibling.children.get(i));
            }
        }

        for (int i = idx + 1; i < numKeys; ++i) {
            keys.set(i - 1, keys.get(i));
        }

        for (int i = idx + 2; i <= numKeys; ++i) {
            children.set(i - 1, children.get(i));
        }

        child.numKeys += sibling.numKeys + 1;
        numKeys--;
    }







}
package Rubro_Negra;

public class Arvore_Rubro <T extends Comparable<T>>  {

    //Cores dos Nos como booleano
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    //Constantes usadas para imprimir em Vermelho e Preto
    public static final String BLACKCOLOR = "\033[0;30m";   //Cor preta para impressão
    public static final String REDCOLOR = "\033[0;31m";    //Cor vermelha para impressão

    //Node Null usado para as folhas
    private final Node_Rubro<T> nullN = new Node_Rubro<>(null, -1);


    private Node_Rubro<T> root = nullN;


    //Quando a árvore é instanciada, já geramos o NóNULL para usarmos nas inserções e remoções
    public Arvore_Rubro() {
        nullN.cor = BLACK;
        nullN.left = nullN;
        nullN.right = nullN;
        nullN.parent = null;
    }

    //Rotação a Esquerda
    private void leftRotate(Node_Rubro<T> x) {
        Node_Rubro<T> y = x.right;
        x.right = y.left;
        if (y.left != nullN) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == nullN) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    // Rotação à Direita
    private void rightRotate(Node_Rubro<T> y) {
        Node_Rubro<T> x = y.left;
        y.left = x.right;
        if (x.right != nullN) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == nullN) {
            root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        x.right = y;
        y.parent = x;
    }



    // Inserir uma nova chave/No nosso caso Palavra na arvore
    //Repare que a função foi alterada, pois quando encontramos uma Objeto igual ao que já está na Arvore, apenas adicionamos esse novo indeice no seu Array
    //O Objeto a que me refiro é a String
    public void insert(T palavra, int index) {
        Node_Rubro<T> newNode = new Node_Rubro<>(palavra, index);
        Node_Rubro<T> y = nullN;
        Node_Rubro<T> x = root;

        newNode.left = nullN;
        newNode.right = nullN;

        boolean existe_igual = false;
        while (x != nullN && existe_igual == false) {
            y = x;
            if (newNode.data.compareTo(x.data) == 0) {
                x.addindex(index);
                existe_igual = true;
            } else {
                if (newNode.data.compareTo(x.data) < 0) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
        }

        if (existe_igual == false){
            newNode.parent = y;
            if (y == nullN) {
                root = newNode;
                root.parent = nullN;
            } else if (newNode.data.compareTo(y.data) < 0) {
                y.left = newNode;
            } else {
                y.right = newNode;
            }
            newNode.cor = RED;
            fixInsert(newNode);
        }
    }

    // Função para onsertar as Inserções
    private void fixInsert(Node_Rubro<T> z) {
        while (z.parent != nullN && z.parent.cor == RED) {
            if (z.parent == z.parent.parent.left) {
                Node_Rubro<T> y = z.parent.parent.right;
                if (y != nullN && y.cor == RED) {
                    z.parent.cor = BLACK;
                    y.cor = BLACK;
                    z.parent.parent.cor = RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.cor = BLACK;
                    z.parent.parent.cor= RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                Node_Rubro<T> y = z.parent.parent.left;
                if (y != nullN && y.cor == RED) {
                    z.parent.cor = BLACK;
                    y.cor = BLACK;
                    z.parent.parent.cor = RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.cor = BLACK;
                    z.parent.parent.cor = RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.cor = BLACK;
    }

    //Visitação Em Pre Ordem para Imprimir a Arvore
    public void preOrder(Node_Rubro<T> node) {
        if (node != nullN) {
            imprime_node(node);
            preOrder(node.left);
            preOrder(node.right);
        }
        if (node == this.root) {
            System.out.println();
        }

    }

    //Imprime um No. Imprimir a Palavra e os elementos do Array, que são as linhas em que aquela palavra aparece
    public void imprime_node(Node_Rubro <T> node) {
        if (node != nullN){
            if (node.cor == RED) {
                //Impressao em cor preta
                System.out.print(REDCOLOR + node.data + ": ");
                for(int i = 0; i < node.linhas.size(); i++) {
                    if (i != node.linhas.size() - 1){
                        System.out.print(node.linhas.get(i) +",");
                    }
                    else {
                        System.out.print(node.linhas.get(i)+" ");
                    }
                }
            }
            else {
                //Impressao em cor preta
                System.out.print(BLACKCOLOR + node.data + ": ");
                for(int i = 0; i < node.linhas.size(); i++) {
                    if (i != node.linhas.size() - 1){
                        System.out.print(node.linhas.get(i) +",");
                    }
                    else {
                        System.out.print(node.linhas.get(i)+" ");
                    }
                }
            }
            System.out.print(BLACKCOLOR + "E: "+node.left.data+" ");
            System.out.println(BLACKCOLOR + "D: "+node.right.data);

        }


        System.out.print(BLACKCOLOR);

    }


    //Procurar na Arvore, se nao encontrar imprime que nao encontrou
    public Node_Rubro<T> search(T data) {
        Node_Rubro<T> current = root;
        while (current != nullN) {
            int cmp = data.compareTo(current.data);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current;
            }
        }
        System.out.println("Nó não Encontrado");
        return nullN;
    }

    // Função de Remover, funciona quase que perfeito, ao chamar a fixremove que temos bugs
    public void remove(T data) {
        Node_Rubro<T> node = search(data);
        if (node == nullN) return;

        Node_Rubro<T> y = node;
        Node_Rubro<T> x;
        boolean yOriginalColor = y.cor;

        if (node.left == nullN) {
            x = node.right;
            transplant(node, node.right);
        } else if (node.right == nullN) {
            x = node.left;
            transplant(node, node.left);
        } else {
            y = minimum(node.right);
            yOriginalColor = y.cor;
            x = y.right;
            if (y.parent == node) {
                if (x != nullN) x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = node.right;
                y.right.parent = y;
            }
            transplant(node, y);
            y.left = node.left;
            y.left.parent = y;
            y.cor = node.cor;
        }

        if (yOriginalColor == BLACK) {
            nullN.parent = y;
            fixRemove(x);
        }

        nullN.parent = null;
    }



    //Função para Consertar a Remoção
    //Funciona parcialmente Não conseguimos consertar o erro
    private void fixRemove(Node_Rubro<T> x) {
        while (x != root && x.cor == BLACK) {
            if (x == x.parent.left) {

                Node_Rubro<T> w = x.parent.right;
                if (w.cor == RED) {
                    w.cor = BLACK;
                    x.parent.cor = RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.cor == BLACK && w.right.cor == BLACK) {
                    w.cor = RED;
                    x = x.parent;
                } else {
                    if (w.right.cor == BLACK) {
                        w.left.cor = BLACK;
                        w.cor = RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    w.cor = x.parent.cor;
                    x.parent.cor = BLACK;
                    w.right.cor = BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {

                Node_Rubro<T> w = x.parent.left;
                if (w.cor == RED) {
                    w.cor = BLACK;
                    x.parent.cor = RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.right.cor == BLACK && w.left.cor == BLACK) {
                    w.cor = RED;
                    x = x.parent;
                } else {
                    if (w.left.cor == BLACK) {
                        w.right.cor = BLACK;
                        w.cor = RED;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    w.cor = x.parent.cor;
                    x.parent.cor = BLACK;
                    w.left.cor = BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }

        }


        x.cor = BLACK;
    }

    // Função que transpoem um nó com ou outro, ou seja, faz eles trocarem de lugar
    //Necessário na remoção
    private void transplant(Node_Rubro<T> u, Node_Rubro<T> v) {
        if (u.parent == nullN) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    //Achar o minimo a patir de um determinado nó, mesmo que encontrar o sucessor do Nó a ser retirado
    private Node_Rubro<T> minimum(Node_Rubro<T> node) {
        while (node.left != nullN) {
            node = node.left;
        }
        return node;
    }



    //Retornar a Raiz
    public Node_Rubro<T> getRoot() {
        return root;
    }


}

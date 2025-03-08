package TreeAVL;


public class AVLTree<T extends Comparable<T>> {

    private AVLNode<T> root;
    private int max_height_difference; //diferença de altura entre as subarvores


    public AVLTree(int max_height_difference) {
        this.max_height_difference = max_height_difference;
    }


    private int height(AVLNode<T> N) {// altura do nó
        if (N == null)
            return 0;
        return N.height;
    }


    private int max(int a, int b) {
        return (a > b) ? a : b;
    }


    private AVLNode<T> rightRotate(AVLNode<T> y) {//rotacao a direita ------------------------------------------
        AVLNode<T> x = y.left;
        AVLNode<T> x_r = x.right;

        x.right = y;
        y.left = x_r;

        y.height = max(height(y.right), height(y.left)) + 1;
        x.height = max(height(x.right), height(x.left)) + 1;


        return x;
    }


    private AVLNode<T> leftRotate(AVLNode<T> x) {//rotacao a esquerda --------------------------------------------
        AVLNode<T> y = x.right;
        AVLNode<T> T2 = y.left;


        y.left = x;
        x.right = T2;


        x.height = max(height(x.right), height(x.left)) + 1;
        y.height = max(height(y.right), height(y.left)) + 1;


        return y;
    }


    private int getBalance(AVLNode<T> N) {//calcula fator de balanceamento
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    public void insert(T key, int line) {
        root = insert(root, key, line);
    }

    private AVLNode<T> insert(AVLNode<T> node, T key, int line) {

        if (node == null) { //insere em um novo nó folha
            return new AVLNode<>(key, line);
        }


        if (key.compareTo(node.key) < 0) { //menor
            node.left = insert(node.left, key, line);
        }
        else if (key.compareTo(node.key) > 0) {//maior
            node.right = insert(node.right, key, line);
        }
        else {
            node.addLine(line); //atualiza as linhas caso o nó ja esteja na arvore
            return node;
        }

        node.height = max(height(node.left), height(node.right)) +1;

        int balance = getBalance(node);//verifica o balanceamento


        // rotações para o caso de a arvore estiver desbalanceada


        if (balance > max_height_difference && getBalance(node.left) >= 0)//verifica se é necessario rotação a direita
            return rightRotate(node);


        if (balance < -max_height_difference && getBalance(node.right) <= 0)// verifica se é necessario rotação a esquerda
            return leftRotate(node);


        if (balance > max_height_difference && getBalance(node.left) < 0) {//rotção dupla a direita
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }


        if (balance < -max_height_difference && getBalance(node.right) > 0) { //rotação dupla a esquerda
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }


        return node;
    }

    public void remove(T key) {
        root = remove(root, key);
    }

    private AVLNode<T> remove(AVLNode<T> node, T key) {
        if (node == null){
            return null;
        }


        if (key.compareTo(node.key) < 0) //menor
            node.left = remove(node.left, key);
        else if (key.compareTo(node.key) > 0)//maior
            node.right = remove(node.right, key);

        else {//se for igual

            if ((node.left == null) || (node.right == null)) { //se tiver um ou nenhum filho
                AVLNode<T> temp = null;
                if (temp == node.left)
                    temp = node.right;// temp recebe o filho a direita
                else
                    temp = node.left;//ou filho a esquerda

                //se for nó folha
                if (temp == null) {
                    temp = node;
                    node = null; //remove o nó
                }
                else {
                    node = temp; //substitui o nó a ser removido pelo seu filho
                }


                temp = null;

            } else {
                //se tiver 2 filhos, pega o sucessor lógico
                AVLNode<T> temp = logicalSuccessor(node.right);
                node.key = temp.key; //copia os dados do sucessor
                //remove o sucessor do seu antigo nó
                node.right = remove(node.right, temp.key);
            }
        }

        if (node == null)
            return node;

        node.height = max(height(node.left), height(node.right)) +1;

        int balance = getBalance(node);//verifica o balanceamento


        // rotações para o caso de a arvore estiver desbalanceada


        if (balance > max_height_difference && getBalance(node.left) >= 0)//verifica se é necessario rotação a direita
            return rightRotate(node);


        if (balance < -max_height_difference && getBalance(node.right) <= 0)// verifica se é necessario rotação a esquerda
            return leftRotate(node);


        if (balance > max_height_difference && getBalance(node.left) < 0) {//rotção dupla a direita
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }


        if (balance < -max_height_difference && getBalance(node.right) > 0) { //rotação dupla a esquerda
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private AVLNode<T> logicalSuccessor(AVLNode<T> node) {
        AVLNode<T> current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }


    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(AVLNode<T> node) {
        if (node != null) {
            System.out.println("Palavra: " + node.key + " (Linhas: " + node.lines + ") ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }


    public void search(T key) {
        //para contar os passos
        int[] steps = {0};

        AVLNode<T> result = search(root, key, steps); //acessa a outra função search

        // Verifica se o nó foi encontrado
        if (result != null) {
            System.out.println("Palavra '" + key + "' encontrada nas seguintes linhas:");
            System.out.println("Linha: " + result.lines);
            System.out.println("Número de passos: " + steps[0]);
        } else {
            System.out.println("Chave '" + key + "' não encontrada na árvore.");
            System.out.println("Número de passos: " + steps[0]);
        }
    }

    private AVLNode<T> search(AVLNode<T> node, T key, int[] steps) {
        steps[0]++;
        if (node == null || node.key.equals(key)) {
            return node;
        }



        if (key.compareTo(node.key) < 0) {
            return search(node.left, key, steps);
        }
        return search(node.right, key, steps);
    }
}

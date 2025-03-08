import Hash.HashTable;
import Hash.ProbingType;
import Hash.Reader;
import TreeAVL.AVLReader;
import TreeAVL.AVLTree;
import Arvore_B.Arvore_B;
import Rubro_Negra.RubroReader;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        ProbingType probingType = null;
        String filePath = "src/texto.txt";
        int x = 0;
        HashTable hashtable = null;
        int balance_factor = 0;


        //


        Scanner scanner = new Scanner(System.in);


        boolean run = true;

        //menu-------------------------------------------------------------------------------------------------
        while (run) {

            System.out.println("--------  ÍNDICE REMISSIVO --------");
            System.out.println("--------        MENU       --------");

            System.out.println("Problema 1:");
            System.out.println("[1] - Utilizar Tabela Hash");
            System.out.println("[2] - Utilizar Árvore AVL");
            System.out.println("[3] - Utilizar Árvore Rubro-Negra");
            System.out.println("[4] - Utilizar Árvore B");

            System.out.println("[5] - Sair");
            System.out.println();
            System.out.print("Selecione uma opção: ");

            int escolha = 0;

            try {
                escolha = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada Inválida");
                scanner.nextLine();
            }


            switch (escolha) {
                case 1:
                    //escolhendo sondagem
                    System.out.println("Digite 1 para escolher Tentativa Linear ou digite 2 para escolher Tentativa Quadrática: ");
                    int probing = 0;
                    boolean next = false;
                    while(!next) {

                        try {
                            probing = scanner.nextInt();
                            if (probing == 1) {
                                probingType = ProbingType.LINEAR;
                                next = true;

                            } else if (probing == 2) {
                                probingType = ProbingType.QUADRATIC;
                                next = true;

                            } else {
                                System.out.println("Entrada Inválida");
                            }
                            hashtable = new HashTable<>(probingType);
                        } catch (Exception e) {
                            System.out.println("Entrada Inválida");
                            scanner.nextLine();
                        }

                    }



                    //---------------------------------------------------------------------------------------------------------------
                    System.out.println("Insira um valor inteiro X: o índice armazenará palavras maiores que X: ");
                    try {
                        x = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Entrada Inválida");
                        scanner.nextLine();
                    }

                    Reader reader = new Reader(filePath, x, hashtable); //----------------------------------- passar
                    try {
                        reader.readStoreWords();

                    } catch (IOException e) {
                        System.err.println("Erro ao ler o arquivo: " + e.getMessage());
                    }


                    //-----------------------------------------------------------------
                    //menu interno - buscar ou remover ou imprimir
                    boolean menu_interno1 = true;

                    while (menu_interno1) {
                        System.out.println("\n-----  TABELA HASH -----\n");
                        System.out.println("[1] - Buscar palavra:");
                        System.out.println("[2] - Remover palavra:");
                        System.out.println("[3] - Imprimir Índice:");
                        System.out.println("[4] - Voltar.");


                        int opcao = 0;
                        try {
                            opcao = scanner.nextInt();
                            scanner.nextLine();
                        } catch (Exception e) {
                            System.out.println("Entrada Inválida");
                            scanner.nextLine();
                        }
                        switch (opcao) {
                            case 1:
                                String palavra = "queijo";
                                System.out.println("Digite a palavra que deseja buscar: ");
                                palavra = scanner.nextLine();

                                reader.getWord(palavra);


                                break;
                            case 2:
                                System.out.println("Digite a palavra que deseja remover: ");
                                String remover = scanner.nextLine();
                                hashtable.remove(remover);


                                break;

                            case 3:
                                reader.printHashTable();

                                break;

                            case 4:
                                menu_interno1 = false;
                                break;

                            default:
                                System.out.println("Opção inválida.");
                                break;


                        }

                    }

                    break;

                case 2:

                    System.out.println("Insira um valor inteiro X para definir o limite do fator de balanceamento:  ");
                    try {
                        balance_factor = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Entrada Inválida");
                        scanner.nextLine();
                    }


                    System.out.println("Insira um valor inteiro X: o índice armazenará palavras maiores que X: ");
                    try {
                        x = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Entrada Inválida");
                        scanner.nextLine();
                    }

                    AVLReader reader2 = new AVLReader(filePath, x, balance_factor); //-----------------------------------

                    //menu interno 2 ------------------------------------------------------------------------
                    boolean menu_interno2 = true;

                    while (menu_interno2) {
                        System.out.println("\n-----  ÁRVORE AVL -----\n");
                        System.out.println("[1] - Buscar palavra:");
                        System.out.println("[2] - Remover palavra:");
                        System.out.println("[3] - Imprimir Índice:");
                        System.out.println("[4] - Voltar.");


                        int opcao = 0;
                        try {
                            opcao = scanner.nextInt();
                            scanner.nextLine();
                        } catch (Exception e) {
                            System.out.println("Entrada Inválida");
                            scanner.nextLine();
                        }
                        switch (opcao) {
                            case 1:
                                String palavra = "queijo";
                                System.out.println("Digite a palavra que deseja buscar: ");
                                palavra = scanner.nextLine();

                                reader2.searchKeyInAVL(palavra);

                                break;

                            case 2:
                                System.out.println("Digite a palavra que deseja remover: ");
                                String remover = scanner.nextLine();
                                reader2.removeKeyFromAVL(remover);


                                break;

                            case 3:
                                reader2.printAvlTree();

                                break;

                            case 4:
                                menu_interno2 = false;
                                break;

                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }

                    }
                    break;

                case 3:
                    Arvore_Rubro_Negra();
                    break;

                case 4:
                    Arvore_B();
                    break;

                case 5:
                    System.out.println("Agradecemos por usar nossos Algoritmos!");
                    run = false;
                    break;
                default:
                    System.out.println();
            }

        }

        scanner.close();


    }

    public static void Arvore_B() {

        //Função para mostrar a Implmentação da Arvore B
        Scanner scanner = new Scanner(System.in);
        String filePath = "Arquivos/btree.dat";

        Arvore_B<Integer> loadedBTree = Arvore_B.loadFromFile(filePath);
        if (loadedBTree != null) {
            System.out.println("Árvore B carregada do arquivo:");
            loadedBTree.printTree();

        } else {
            System.out.println("Não temos Árvore B carregada! Devemos criar uma nova!");
            int entr;
            System.out.print("Digite a ordem da árvore B que você deseja criar: ");
            entr = scanner.nextInt();
            loadedBTree = new Arvore_B<Integer>(entr, filePath);
        }


        boolean run = true;
        while (run){
            menu_b();
            int escolha = scanner.nextInt();
            int entrada;
            switch(escolha) {
                case 1:
                    System.out.print("Digite a nova chave: ");
                    entrada = scanner.nextInt();
                    loadedBTree.insert(entrada);
                    loadedBTree.saveToFile();
                    break;
                case 2:
                    System.out.print("Digite a chave que deseja remover: ");
                    entrada = scanner.nextInt();
                    loadedBTree.remove(entrada);
                    loadedBTree.saveToFile();
                    break;
                case 3:
                    System.out.println("Árvore B carregada do arquivo:");
                    loadedBTree.printTree();
                    break;
                case 4:
                    run = false;
                    break;
                default:
                    System.out.println("Entrada inválida: ");

            }

        }



    }

    public static void menu_b() {
        //Menu usado na função da Arvore B
        System.out.println();
        System.out.println("---- MENU: ARVORE B ----");
        System.out.println("[1] Inserir Nova Chave");
        System.out.println("[2] Remover Chave");
        System.out.println("[3] Mostrar Estrutura da Arvore");
        System.out.println("[4] Sair");
        System.out.println();
    }

    public static void Arvore_Rubro_Negra() {

        //Sem Menu Teste Feitos aqui inserindo os valores com um leve bug na remoção
        RubroReader BlackRed = new RubroReader("Arquivos/texto.txt", 10);
        System.out.println("Árvore Rubro após ler do Arquivo:");
        BlackRed.printRubro();


        BlackRed.insert("pneu", 10);
        System.out.println("Árvore Rubro após inserir a palavra pneu: ");
        BlackRed.printRubro();


        BlackRed.remove("pneu");
        System.out.println("Árvore Rubro após remover a palavra pneu: ");
        BlackRed.printRubro();

        BlackRed.remove("problematicas");
        System.out.println("Árvore Rubro após remover a palavra problematicas: ");
        BlackRed.printRubro();

        BlackRed.remove("imediatamente");
        System.out.println("Árvore Rubro após remover a imediatamente: ");
        BlackRed.printRubro();


        BlackRed.remove("rafjeujfejsdi");
        System.out.println("Árvore Rubro após remover a rafjeujfejsdi: ");
        BlackRed.printRubro();
    }


}







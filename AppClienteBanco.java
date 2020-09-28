package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // procura o serviço no RMI Registry local. Perceba que o cliente não connhece a implementação do servidor,
        // apenas a interface
        Registry registry = LocateRegistry.getRegistry(1099);
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        menu();
        Scanner entrada = new Scanner(System.in);
        int opcao = entrada.nextInt();

        while(opcao != 9) {
            switch (opcao) {
                case 1: {
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    System.out.println("A conta eh " + conta);
                    //chamada ao método remoto, como se fosse executar localmente
                    System.out.println(banco.saldo(conta));
                    System.out.println("\n*****************************************\n");
                    break;
                }
                case 2: {
                    //chamada ao método remoto, como se fosse executar localmente
                    System.out.println(banco.quantidadeContas());
                    System.out.println("\n*****************************************\n");
                    break;
                }
                case 3: {
                    //Cadastro de Nova Conta
                    System.out.println("Digite o numero da conta:\n");
                    String numeroConta = entrada.next();
                    System.out.println("Digite o saldo inicial da conta:\n");
                    String saldoConta = entrada.next();

                    //Chamada método remoto
                    banco.adicionarConta(numeroConta, saldoConta);
                    System.out.println("Conta cadastrada com sucesso! \n");
                    System.out.println("\n*****************************************\n");
                    break;
                }
            }
            menu();
            opcao = entrada.nextInt();
        }
    }

    public static void menu() {
        System.out.println("Olá, Samuel Deschamps Cabral");
        System.out.println("\n=== BANCO RMI (ou FMI?!) ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Criar nova conta");
        System.out.println("9 - Sair");
    }

}

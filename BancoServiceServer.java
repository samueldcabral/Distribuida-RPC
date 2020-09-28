package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

//    private Map<String, Double> saldoContas;
    private List<Conta> Contas;

    public BancoServiceServer() throws RemoteException {
        Contas = new ArrayList<Conta>();
        Contas.add(new Conta("1", 100.0));
        Contas.add(new Conta("2", 220.0));
        Contas.add(new Conta("3", 303.0));

//        saldoContas = new HashMap<>();
//        saldoContas.put("1", 100.0);
//        saldoContas.put("2", 156.0);
//        saldoContas.put("3", 950.0);
    }

    @Override
    public double saldo(String conta) throws RemoteException {
        return Contas
                .stream()
                .filter(c -> c.getNumero().equals(conta))
                .collect(Collectors.toList())
                .get(0)
                .getSaldo();
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return Contas.size();
    }

    @Override
    public void adicionarConta(String numeroConta, String saldoConta) throws RemoteException{
        try {
            Contas.add(new Conta(numeroConta, Double.parseDouble(saldoConta)));
        }catch (Exception e){
            System.out.println("error " + e);
        }

    }
}

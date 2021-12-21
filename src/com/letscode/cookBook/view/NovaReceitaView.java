package com.letscode.cookBook.view;

import com.letscode.cookBook.controller.Catalogo;
import com.letscode.cookBook.domain.Ingrediente;
import com.letscode.cookBook.domain.Receita;
import com.letscode.cookBook.domain.Rendimento;
import com.letscode.cookBook.enums.Categoria;
import com.letscode.cookBook.enums.TipoMedida;
import com.letscode.cookBook.enums.TipoRendimento;

import java.util.InputMismatchException;
import java.util.List;


import java.util.ArrayList;
import java.util.Scanner;

public class NovaReceitaView {
    Catalogo catalogo;
    Scanner scanner;
    Receita receita;
    String nome;
    int tempoDePreparo;
    Categoria categoria;
    Rendimento rendimento;
    Ingrediente ingrediente;
    List<Ingrediente> ingredientes;
    List<String> etapasDePreparo;


    public NovaReceitaView() {
        this.scanner = new Scanner(System.in);
    }

    public void askNome() {
        System.out.println("Qual o nome da receita?");
        nome = scanner.next();
        if (nome.isBlank()) {
            System.out.println("Nome inválido!");
            askNome();
        }
    }

    public void askTempo(){
        System.out.println("Qual o tempo de preparo (em minutos)?");
        try{
            tempoDePreparo = scanner.nextInt();
            if (tempoDePreparo <= 0) {
                System.out.println("Tempo inválido!");
                askTempo();
            }else{
                this.receita.setTempoPreparo(tempoDePreparo);
            }
        }catch (InputMismatchException e){
            System.out.println("Entrada Inválida");
            scanner.next();
            askTempo();
        }

    }

    public void askRendimento(){
        System.out.println("Qual o tipo de rendimento?");
        try{
            for (TipoRendimento red : TipoRendimento.values()) {
                System.out.printf("%d - %s%n", red.ordinal(), red.name());
            }
            int rendimentoIndex = scanner.nextInt();
            if (rendimentoIndex < 0 || rendimentoIndex  >= TipoRendimento.values().length) {
                System.out.println("Tipo de rendimento inválido!");
                askRendimento();
            }else{
                TipoRendimento tipoRendimento = TipoRendimento.values()[rendimentoIndex];
                System.out.println("Qual a quantidade do rendimento?");
                int qtdRendimento = scanner.nextInt();
                if (qtdRendimento <= 0) {
                    System.out.println("Quantidade inválida!");
                    askRendimento();
                }
                this.rendimento = new Rendimento(qtdRendimento, tipoRendimento);
            }
        }catch (InputMismatchException e){
            System.out.println("Entrada Inválida para Rendimento, tente novamente!");
            scanner.next();
            askRendimento();
        }

    }

    public void askIgrediente() {
        boolean flag = true;
        ingredientes = new ArrayList<Ingrediente>();
        System.out.println("Defina os ingredientes: ");
        try{
            do {
                System.out.println("Qual o tipo de medida do ingrediente?");
                for (TipoMedida med : TipoMedida.values()) {
                    System.out.printf("%d - %s%n", med.ordinal(), med.name());
                }
                int medidaIndex = scanner.nextInt();
                if (medidaIndex < 0 || medidaIndex >= TipoMedida.values().length) {
                    System.out.println("Tipo de medida inválido!");
                    askIgrediente();
                } else {
                    TipoMedida tipoMedida = TipoMedida.values()[medidaIndex];
                    System.out.println("Qual a quantidade do ingrediente?");
                    int qtdIngrediente = scanner.nextInt();
                    if (qtdIngrediente <= 0) {
                        System.out.println("Quantidade inválida!");
                        askIgrediente();
                    } else {
                        System.out.println("Qual o nome da ingrediente?");
                        String nome = scanner.next();
                        if (nome.isBlank()) {
                            System.out.println("Nome inválido!");
                            askIgrediente();
                        }
                        ingrediente = new Ingrediente(nome, qtdIngrediente, tipoMedida);
                        ingredientes.add(ingrediente);

                        System.out.println("Deseja adicionar mais um igrediente?");
                        System.out.println("1 - SIM");
                        System.out.println("2 - NÃO");
                        int condicao = scanner.nextInt();
                        switch (condicao) {
                            case 1:
                                continue;
                            case 2:
                                this.receita.setIngredientes((Ingrediente[]) ingredientes.toArray(new Ingrediente[0]));
                                flag = false;
                                break;
                        }
                    }
                }
            }while (flag) ;
        }catch (InputMismatchException e){
            System.out.println("Entrada Inválida para Ingrediente, tente novamente!");
            scanner.next();
            askIgrediente();
        }

    }


    public void askCategoria() {
        System.out.println("Qual a categoria da receita?");
        for (Categoria cat : Categoria.values()) {
            System.out.printf("%d - %s%n", cat.ordinal(), cat.name());
        }
        try {
            int categoriaIndex = scanner.nextInt();
            if (categoriaIndex < 0 || categoriaIndex  >= Categoria.values().length) {
                System.out.println("Categoria inválida!");
                askCategoria();
            }else{
                this.categoria = Categoria.values()[categoriaIndex];
            }
        }catch (InputMismatchException e){
            System.out.println("Entrada Inválida!");
            scanner.next();
            askCategoria();
        }

    }

    public void askModoPreparo(){
        etapasDePreparo = new ArrayList<String>();
        boolean flag = true;
        System.out.println("Defina em etapas o modo de preparo: ");
        try {
            do {
                scanner.nextLine();
                String etapa = scanner.nextLine();
                etapasDePreparo.add(etapa);
                System.out.println("Deseja adicionar mais uma etapa de preparo?");
                System.out.println("1 - SIM");
                System.out.println("2 - NÃO");
                int condicao = scanner.nextInt();
                switch (condicao) {
                    case 1:
                        continue;
                    case 2:
                        this.receita.setModoPreparo((String[]) etapasDePreparo.toArray(new String[0]));
                        flag = false;
                        break;
                }
            }while (flag);
        }catch (InputMismatchException e){
            System.out.println("Entrada Inválida!");
            scanner.next();
            askModoPreparo();
        }


    }

    public void setReceita(){
        receita = new Receita(this.nome, this.categoria, this.rendimento);
    }

    public Receita getReceita(){
        return this.receita;
    }

    public void addReceita(Receita nova){
        this.catalogo.add(nova);
    }


}

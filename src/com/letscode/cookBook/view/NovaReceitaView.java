package com.letscode.cookBook.view;

import com.letscode.cookBook.controller.Catalogo;
import com.letscode.cookBook.domain.Receita;
import com.letscode.cookBook.domain.Rendimento;
import com.letscode.cookBook.enums.Categoria;
import com.letscode.cookBook.enums.TipoRendimento;

import java.util.Scanner;

public class NovaReceitaView {
    Catalogo catalogo;
    Scanner scanner;
    Receita receita;
    String nome;
    Categoria categoria;
    Rendimento rendimento;

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

    public void askRendimento(){
        System.out.println("Qual o tipo de rendimento?");
        for (TipoRendimento red : TipoRendimento.values()) {
            System.out.printf("%d - %s%n", red.ordinal(), red.name());
        }
        int rendimentoIndex = scanner.nextInt();
        if (rendimentoIndex < 0 || rendimentoIndex  >= Categoria.values().length) {
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
    }

    public void askCategoria() {
        System.out.println("Qual a categoria da receita?");
        for (Categoria cat : Categoria.values()) {
            System.out.printf("%d - %s%n", cat.ordinal(), cat.name());
        }
        int categoriaIndex = scanner.nextInt();
        if (categoriaIndex < 0 || categoriaIndex  >= Categoria.values().length) {
            System.out.println("Categoria inválida!");
            askCategoria();
        }else{
            this.categoria = Categoria.values()[categoriaIndex];
        }
    }

    public void setReceita(){
        receita = new Receita(this.nome, this.categoria, this.rendimento);
//        System.out.println(receita.getNome());
//        System.out.println(receita.getCategoria());
        //this.catalogo.add(receita);
    }


}

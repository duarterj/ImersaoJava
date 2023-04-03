package org.example;

import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class App {
    public App() {
    }
    public static void main(String[] args) throws Exception {

        String url = "https://duarte-linguagens.fly.dev/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoStickers();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        List <Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradorDeFigurinha();

        for (int i = 0; i < 6; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}
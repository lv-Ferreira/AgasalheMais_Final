package com.fatec.agasalhemais.model;

import android.provider.ContactsContract;

public class Pedido {
    //ESSA CLASSE É RESPONSAVEL POR RECEBER E ATRIBUIR POR MEIO DO CONTRUTOR, OS DADOS NAS SUAS VARIAVEIS;
    private String usuario_pedido;
    private String item_pedido;
    private String item_variacao;
    private String item_icone;
    private String item_data;
    private String item_id;

    public String getItem_id() { return item_id; }

    public void setItem_id(String item_id) { this.item_id = item_id; }

    public String getItem_data() {
        return item_data;
    }

    public void setItem_data(String item_data) {
        this.item_data = item_data;
    }

    public String getItem_icone() {
        return item_icone;
    }

    public void setItem_icone(String item_icone) {
        this.item_icone = item_icone;
    }

    public String getUsuario_pedido() {
        return usuario_pedido;
    }

    public void setUsuario_pedido(String usuario_pedido) {
        this.usuario_pedido = usuario_pedido;
    }

    public String getItem_pedido() {
        return item_pedido;
    }

    public void setItem_pedido(String item_pedido) {
        this.item_pedido = item_pedido;
    }

    public String getItem_variacao() {
        return item_variacao;
    }

    public void setItem_variacao(String item_variacao) {
        this.item_variacao = item_variacao;
    }


    public static class construtorPedido {
        private String usuario_pedido;
        private String item_pedido;
        private String item_variacao;
        private String item_icone;
        private String item_data;
        private String item_id;

        public construtorPedido setItem_id(String item_id) {
            this.item_id = item_id;
            return this;
        }

        public construtorPedido setItem_data(String item_data) {
            this.item_data = item_data;
            return this;
        }

        public construtorPedido setItem_icone(String item_icone) {
            this.item_icone = item_icone;
            return this;
        }

        public construtorPedido setUsuario_pedido(String usuario_pedido) {
            this.usuario_pedido = usuario_pedido;
            return this;
        }

        public construtorPedido setItem_pedido(String item_pedido) {
            this.item_pedido = item_pedido;
            return this;
        }

        public construtorPedido setItem_variacao(String item_variacao) {
            this.item_variacao = item_variacao;
            return this;
        }

        private construtorPedido(){}

        public static construtorPedido construtor(){
            return new construtorPedido();
        }

        public Pedido build(){
            Pedido pedido = new Pedido();
            pedido.usuario_pedido = usuario_pedido;
            pedido.item_pedido = item_pedido;
            pedido.item_variacao = item_variacao;
            pedido.item_icone = item_icone;
            pedido.item_data = item_data;
            pedido.item_id = item_id;
            return pedido;
        }
    }
}

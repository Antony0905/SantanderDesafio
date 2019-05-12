package com.santander.enums;

public enum PerfilSistemaCredenciado {

	ADMIN(1, "ROLE_ADMIN"), CLIENTE(2, "ROLE_CLIENTE");

	private int cod;
	private String descricao;

	private PerfilSistemaCredenciado(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static PerfilSistemaCredenciado toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (PerfilSistemaCredenciado x : PerfilSistemaCredenciado.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}

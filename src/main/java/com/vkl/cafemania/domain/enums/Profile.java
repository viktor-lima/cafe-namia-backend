package com.vkl.cafemania.domain.enums;

public enum Profile {
	ADMIN(1, "ROLE_ADMIN"), COLLABORATOR(2, "ROLE_COLLABORATOR");

	private int cod;
	private String descricao;

	private Profile(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Profile toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Profile x : Profile.values()) {
			if (cod.equals(x.getCod()))
				return x;
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod); 
	}
	
}

package br.com.fintech.utils;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.fintech.entity.BancoCorretora;
import br.com.fintech.entity.CategoriaGasto;
import br.com.fintech.entity.Sexo;
import br.com.fintech.entity.TipoInvestimento;

public class ListadorEnum {
	
	public static List <String> listarBancoCorretora(){
		Set <BancoCorretora> bancoCorretoras = EnumSet.allOf(BancoCorretora.class);
		List <String> listaBancoCorretora = bancoCorretoras.stream().map(b -> Capitalizer.capitalize(b.name())).collect(Collectors.toList());
		return listaBancoCorretora;
	}
	
	public static List <String> listarCategoriaGasto(){
		Set <CategoriaGasto> CategoriaGastos = EnumSet.allOf(CategoriaGasto.class);
		List <String> listaCategoriaGasto = CategoriaGastos.stream().map(c -> c.getName()).collect(Collectors.toList());
		return listaCategoriaGasto;
		
	}
	public static List <String> listarTipoInvestimento(){
		Set <TipoInvestimento> TipoInvestimentos = EnumSet.allOf(TipoInvestimento.class);
		List <String> listaTipoInvestimento = TipoInvestimentos.stream().map(t -> t.getName()).collect(Collectors.toList());
		return listaTipoInvestimento;
	}
	public static List <String> listarTipoSexo(){
		Set <Sexo> TipoSexos = EnumSet.allOf(Sexo.class);
		List <String> listaTipoSexo = TipoSexos.stream().map(t -> t.getName()).collect(Collectors.toList());
		return listaTipoSexo;
	}
	
}

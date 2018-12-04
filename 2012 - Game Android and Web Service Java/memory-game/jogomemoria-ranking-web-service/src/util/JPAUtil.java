package util;

import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;


public class JPAUtil {
	
	private static Ejb3Configuration config = new Ejb3Configuration();
	
	public static void gerarTabelas(){
		config.configure("jogomemoria.db", null);
		Configuration hbmcfg = config.getHibernateConfiguration();
		SchemaExport schemaExport = new SchemaExport(hbmcfg);
		schemaExport.create(true, true);
		System.out.println("Tabelas criadas com sucesso!");
	}
	
	public static void main(String[] args) {
		
		gerarTabelas();
	}

}

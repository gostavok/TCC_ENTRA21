package br.com.textilsoft;

import java.io.IOException;
import java.sql.SQLException;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import br.com.textilsoft.dao.ContaReceberDAO;



@ApplicationPath("rest")
public class MyApplication extends ResourceConfig {

	public MyApplication() throws IOException {
		packages("br.com.textilsoft.controller");
		
		
		try {
			ContaReceberDAO cr = new ContaReceberDAO();
			cr.alterar();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

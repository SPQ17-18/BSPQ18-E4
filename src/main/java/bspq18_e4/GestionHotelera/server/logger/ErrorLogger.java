package bspq18_e4.GestionHotelera.server.logger;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class ErrorLogger implements Serializable {

	private static final long serialVersionUID = 1L;
	private Logger logger;

	public ErrorLogger() {
		this.logger = Logger.getLogger("Logger");
	}

	public Logger getLogger() {
		return logger;
	}
}

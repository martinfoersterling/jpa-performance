package mafoe.jpaperformance.util;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

public class ErzeugeDdl {

	public static void main(String[] args) {
		new ErzeugeDdl().fuehreAus();
	}

	private static final Logger LOG = LoggerFactory.getLogger(ErzeugeDdl.class);

	private void fuehreAus() {

		LOG.info("Starte Schema-Export");
		LocalSessionFactoryBuilder configuration = new LocalSessionFactoryBuilder(null);

		configuration.scanPackages(getModelPackageNames());
		configuration.setProperty("hibernate.dialect", H2Dialect.class.getName());
		SchemaExport schemaExport = new SchemaExport(configuration);
		schemaExport.setOutputFile(getZieldateipfad());
		schemaExport.setFormat(true);
		schemaExport.setDelimiter(";");
		schemaExport.execute(true, false, false, false);

		LOG.info("Schema exportiert nach {}", getZieldateipfad());
	}

	/**
	 * Der relative Pfad zur Zieldatei, in die das erzeugte DDL geschrieben werden soll. Beispiel:
	 * "src/test/resources/Initialschema.sql"
	 */
	private String getZieldateipfad() {
		return "src/main/resources/Initialschema.sql";
	}

	/**
	 * Der volle Name der Packages, in dem die Entitäten liegen. Beispiel: "de.volkswagen.eplrs.model"
	 */
	private String[] getModelPackageNames() {
		return new String[] {
				"mafoe.jpaperformance.model"
		};
	}
}

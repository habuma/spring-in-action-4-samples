package orders.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

@Configuration
@EnableNeo4jRepositories(basePackages = "orders.db")
public class Neo4jConfig extends Neo4jConfiguration {

	public Neo4jConfig() {
		setBasePackage("orders");
	}

	@Bean(destroyMethod="shutdown")
	public GraphDatabaseService graphDatabaseService() {	
		return new GraphDatabaseFactory()
				.newEmbeddedDatabase("/tmp/graphdb");
	}
}
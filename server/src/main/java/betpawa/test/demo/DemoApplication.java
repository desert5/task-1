package betpawa.test.demo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException, InterruptedException	{
		SpringApplication.run(DemoApplication.class, args);
		Server server = ServerBuilder.forPort(9090).addService(new WalletServer()).build();
		server.start();
		server.awaitTermination();
	}
}

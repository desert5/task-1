package betpawa.test.demo;

import betpawa.test.demo.service.WalletService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@Slf4j
@SpringBootApplication
public class DemoApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public Server grpcServer(@Autowired WalletService walletService) throws IOException
    {
        Server server = ServerBuilder.forPort(9999)
                .addService(walletService)
                .build();
        server.start();
        return server;
    }
}

package pdp.gg_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class GgStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(GgStoreApplication.class, args);
    }
/*
*
* this application is a small copy of the site answer.mail ru
*
*
* You can also do:
*     •optimization of code logic - Tag and Question links
*     •add chat functionality
*     •come up with a logic for the rating
*     •come up with a logic for role with permissions
*     •add the Bann class for ban functionality
*          for users to block users you don’t like for yourself
*     •add a separate class for avatar management
*     •create controllers and services
*          for more detailed management of role creation
*     •add mailing
*     •add logging
* */
}

package com.prodev.spring5.webapp.dbinitializer;

import com.prodev.spring5.webapp.entity.Address;
import com.prodev.spring5.webapp.entity.Organization;
import com.prodev.spring5.webapp.entity.Publisher;
import com.prodev.spring5.webapp.repository.AddressRepository;
import com.prodev.spring5.webapp.repository.OrganizationRepository;
import com.prodev.spring5.webapp.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    PublisherRepository publisherRepository;
    AddressRepository addressRepository;
    OrganizationRepository organizationRepository;



    public DatabaseInitializer(PublisherRepository publisherRepository, AddressRepository addressRepository, OrganizationRepository organizationRepository) {
        this.publisherRepository = publisherRepository;
        this.addressRepository = addressRepository;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Address address=new Address("line1","","yangon","tarmawe","1051");
        Publisher publisher=new Publisher("publisher1",address);
      addressRepository.save(address);
      publisherRepository.save(publisher);

        Organization mainOrg=new Organization("Main Org");
        List<Organization> suborgList=null;
        organizationRepository.save(mainOrg);

            Organization sub1=new Organization("suorg",mainOrg);
            Organization sub11=new Organization("suborg11",sub1);
            Organization sub12=new Organization("suborg12",sub1);
        suborgList=new ArrayList<>();
        suborgList.add(sub11);
        suborgList.add(sub12);

        mainOrg.addChild(sub1);
        organizationRepository.save(sub1);
        sub1.getChildren().addAll(suborgList);
        organizationRepository.saveAll(suborgList);

        Organization sub2=new Organization("suorg",mainOrg);
            Organization sub21=new Organization("suborg21",sub2);
            Organization sub22=new Organization("suborg22",sub2);
        suborgList=new ArrayList<>();
        suborgList.add(sub21);
        suborgList.add(sub22);

        mainOrg.addChild(sub2);
        organizationRepository.save(sub2);
        sub2.getChildren().addAll(suborgList);
        organizationRepository.saveAll(suborgList);

        Organization sub3=new Organization("suorg",mainOrg);
            Organization sub31=new Organization("suborg31",sub3);
            Organization sub32=new Organization("suborg32",sub3);
            Organization sub33=new Organization("suborg33",sub3);
            suborgList=new ArrayList<>();
            suborgList.add(sub31);
           suborgList.add(sub32);
           suborgList.add(sub33);

        mainOrg.addChild(sub3);
       organizationRepository.save(sub3);
       sub3.getChildren().addAll(suborgList);
       organizationRepository.saveAll(suborgList);



      System.out.println("Publisher Count:"+publisherRepository.count());
        System.out.println("Organizaiton Count:"+organizationRepository.count());

        List<Organization> organizationList=organizationRepository.findAll();
        for(Organization o:organizationList){
            System.out.println(o.toString());
        }

    }//end of  run method


}

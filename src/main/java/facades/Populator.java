/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.EntityManagerFactory;

import dtos.PersonDTO;
import entities.*;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
//        FacadeExample fe = FacadeExample.getFacadeExample(emf);
//        fe.create(new RenameMeDTO(new RenameMe("First 1", "Last 1")));
//        fe.create(new RenameMeDTO(new RenameMe("First 2", "Last 2")));
//        fe.create(new RenameMeDTO(new RenameMe("First 3", "Last 3")));
        PersonFacade pf = PersonFacade.getInstance(emf);
        //Address address, String firstName, String lastName, Integer age, String gender, String email, Set<Phone> phones, Set<Hobby> hobbies
        Cityinfo ci = new Cityinfo("Testby", "6969");
        Address a = new Address("Testgade 14", "Th.", ci);
        Person p = new Person(a, "Ib", "Ibsen", 69, "Male", "ib@Ibsen.com");
        pf.create(new PersonDTO(p));
        Phone po = new Phone("420420420","Mobil", p);
    }
    
    public static void main(String[] args) {
        populate();
    }
}

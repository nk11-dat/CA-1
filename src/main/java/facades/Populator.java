/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.EntityManagerFactory;

import dtos.HobbyDTO;
import dtos.PersonDTO;
import entities.*;
import utils.EMF_Creator;

import java.util.HashSet;
import java.util.Set;

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
        HobbyFacade hf = HobbyFacade.getInstance(emf);
        //Address address, String firstName, String lastName, Integer age, String gender, String email, Set<Phone> phones, Set<Hobby> hobbies
        Cityinfo ci = new Cityinfo("Testby", "6969");
        Address a = new Address("Testgade 14", "Th.", ci);
        HobbyDTO hd = hf.getById(12);
//        Hobby h = new Hobby(hd.getName(), hd.getWikiLink(), hd.getCategory(), hd.getType());
        Set<Phone> ps = new HashSet<>();
//        Set<Hobby> hs = new HashSet<>();
//        hs.add(h);
        Person p = new Person(a,"Bo", "Bobsen", 45, "Male", "bo@bobsen.com");
        p.addPhone(new Phone("420420420","Mobil"));
        p.addHobby(new Hobby(hd.getName(), hd.getWikiLink(), hd.getCategory(), hd.getType()));
//        Phone po = new Phone("420420420","Mobil", p);
//        ps.add(po);
//        p.setPhones(ps);
//        p.setHobbies(hs);
        pf.create(new PersonDTO(p));
    }
    public static void main(String[] args) {
        populate();
    }
}

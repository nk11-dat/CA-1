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
        PersonFacade pf = PersonFacade.getInstance(emf);
        HobbyFacade hf = HobbyFacade.getInstance(emf);
        Cityinfo ci = new Cityinfo("Testby", "6969"); //TODO: find en by der findes istedet for og brug den... din abe...
        Address a = new Address("Testgade 14", "Th.", ci);
        HobbyDTO hd = hf.getHobbyDTOById(50);
        Set<Phone> ps = new HashSet<>();
        Person p = new Person(a,"Ib", "Ibsen", 99, "Male", "Ib@Spagettie.com");
        PersonDTO pd = pf.create(p);
        pf.addHobby(pd.getId(), hd.getId());
        pf.addAndCreatePhone(22, new Phone("69696913","Mobil"));
//        FacadeExample fe = FacadeExample.getFacadeExample(emf);
//        fe.create(new RenameMeDTO(new RenameMe("First 1", "Last 1")));
//        fe.create(new RenameMeDTO(new RenameMe("First 2", "Last 2")));
//        fe.create(new RenameMeDTO(new RenameMe("First 3", "Last 3")));
        //Address address, String firstName, String lastName, Integer age, String gender, String email, Set<Phone> phones, Set<Hobby> hobbies
//        Hobby h = new Hobby(hd.getName(), hd.getWikiLink(), hd.getCategory(), hd.getType());
//        Set<Hobby> hs = new HashSet<>();
//        hs.add(h);
//        p.addPhone(new Phone("420420420","Mobil"));
//        p.addHobby(new Hobby(hd.getName(), hd.getWikiLink(), hd.getCategory(), hd.getType()));
//        Phone po = new Phone("420420420","Mobil", p);
//        ps.add(po);
//        p.setPhones(ps);
//        p.setHobbies(hs);
//        pf.create(new PersonDTO(p));
    }
    public static void main(String[] args) {
        populate();
    }
}

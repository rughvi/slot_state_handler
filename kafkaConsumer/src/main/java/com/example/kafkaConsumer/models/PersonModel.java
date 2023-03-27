package com.example.kafkaConsumer.models;

import lombok.NoArgsConstructor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

//@Data
@NoArgsConstructor
@XmlRootElement(name = "person")
public class PersonModel {
    public PersonModel(Integer id, String name, String dob)
    {
        Id = id;
        Name = name;
        DOB = dob;
    }

    @XmlAttribute(name = "id")
    private Integer Id;
    @XmlAttribute(name = "name")
    private String Name;
    @XmlAttribute(name = "dob")
    private String DOB;

    public String getDOB(){
        return this.DOB;
    }

    public Integer getId(){
        return this.Id;
    }

    public String getName(){
        return this.Name;
    }

    public static PersonModel convertFromXml(String xmlString) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(PersonModel.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//        unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Reader reader = new StringReader(xmlString);
        PersonModel personModel = (PersonModel) unmarshaller.unmarshal(reader);
        return personModel;
    }
}

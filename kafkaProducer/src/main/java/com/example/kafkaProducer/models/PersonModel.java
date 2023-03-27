package com.example.kafkaProducer.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
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

    public String convertToXml() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(PersonModel.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Writer writer = new StringWriter();

        marshaller.marshal(this, writer);
        marshaller.marshal(this, System.out);

        String data = writer.toString();

        return data;
    }
}

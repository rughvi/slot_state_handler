package com.example.kafkaProducer;

import com.example.kafkaProducer.models.PersonModel;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;

public class PersonModelToXmlTest {

    private PersonModel personModel;

    @Before
    public void setUp() {
        var localDate = LocalDate.of(2023, 3, 9);
        personModel = new PersonModel(1, "", localDate.toString());
    }

    @Test
    public void testObjectToXml() throws JAXBException, FileNotFoundException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PersonModel.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Writer writer = new StringWriter();

            marshaller.marshal(personModel, writer);
            marshaller.marshal(personModel, System.out);

            String data = writer.toString();
        }
        catch (Exception ex){
            throw  ex;
        }
    }
}

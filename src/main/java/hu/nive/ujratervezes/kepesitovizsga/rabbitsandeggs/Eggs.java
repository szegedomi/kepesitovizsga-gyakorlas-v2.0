package hu.nive.ujratervezes.kepesitovizsga.rabbitsandeggs;

import java.io.BufferedReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Eggs {

    private List<Rabbit> readFromCsv(Path file){
        List<Rabbit> result = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(file)) {
            String line;
            String[] data;
            while((line=br.readLine()) != null){
                data = line.split(";");
                result.add(new Rabbit(data[0], Integer.parseInt(data[1])));
            }
        }
        catch (IOException ioe){
            throw new IllegalStateException("Cannot read file", ioe);
        }
        return result;
    }

    public Rabbit getRabbitWithMaxEggs(){

        Path file = Path.of("./src/main/resources/eggs.csv");

        List<Rabbit> rabbits = new ArrayList<>();
        rabbits = readFromCsv(file);

        Rabbit result = rabbits.get(0);
        for(Rabbit rabbit : rabbits){
            if(rabbit.getEggs() > result.getEggs()){
                result = rabbit;
            }
        }
        return result;
    }

}

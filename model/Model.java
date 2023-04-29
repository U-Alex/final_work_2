package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Model {
    private List<Toy> listToys;
    private List<Integer> listFreq;
    private Integer totalFreq = 0;
    private String path;

    public Model(String path) {
        this.listToys = new ArrayList<>();
        this.listFreq = new ArrayList<>();
        this.path = path;
    }

    public void load() {
        this.listToys.clear();
        this.totalFreq = 0;
        try {
            File file = new File(this.path + "data.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String rec = reader.readLine();
            String[] line = new String[]{};
            while (rec != null) {
                line = rec.split(";");
                this.listToys.add(new Toy(Integer.parseInt(line[0]), line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3])));
                this.totalFreq += Integer.parseInt(line[3]);
                this.listFreq.add(totalFreq);
                rec = reader.readLine();
            }
            reader.close();
            fr.close();
            this.saveToLog("list_loaded");
        } 
        catch (Exception e) { e.printStackTrace(); }
    }

    public void saveToLog(String text) {
        File file = new File(this.path + "log.txt");
        FileWriter fr = null;
        BufferedWriter br = null;
        try {
            fr = new FileWriter(file,true);
            br = new BufferedWriter(fr);
            br.newLine();
            br.write(String.format("%s | %s", LocalDateTime.now().toString(), text));
            br.close();
            fr.close();
        } 
        catch (IOException e) { e.printStackTrace(); } 
    }

    public String takeToy() {
        if (listFreq.size() == 0) return "список пуст";
        Random rnd = new Random();
        Integer take = rnd.nextInt(1, this.totalFreq + 1);
        for (int i = 0; i < listFreq.size(); i++) {
            if (take <= listFreq.get(i)) {
                Toy selectToy = this.listToys.get(i);
                this.saveToLog(String.format("taked_toy: %s | id: %d", selectToy.getName(), selectToy.getId()));
                if (selectToy.takeToy()) { 
                    this.saveToLog(String.format("%s закончились", selectToy.getName()));
                    this.load(); 
                }
                return selectToy.getName();
            }
        }
        return "";
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.listToys.size() == 0)
            return sb.append("список пуст").toString();
        sb.append("---- список призов ----\n");
        for (int i = 0; i < this.listToys.size(); i++) {
            sb.append(String.format("> %d \t", i+1));
            sb.append(listToys.get(i).getName());
            sb.append("\n");
        }
        return sb.toString();
    }

}

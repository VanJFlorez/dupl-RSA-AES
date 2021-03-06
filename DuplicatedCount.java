import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
  
public class DuplicatedCount {
  
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) 
    { 
        List<Map.Entry<String, Integer> > list = 
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet()); 
  
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
          
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    }

    public static Integer getTotalFrequency(HashMap<String, Integer> hm) {
      Integer total = 0;
      for (Map.Entry<String, Integer> en : hm.entrySet()) { 
        total += en.getValue();
      }      
      return total;
    }
  
    public static void main(String[] args) 
    { 
        String str = "INGENIERIA DE SISTEMAS";
        str = str.toUpperCase();

        HashMap<String, Integer> hm = new HashMap<String, Integer>(); 

        for(char c : str.toCharArray()) {
          Integer j = hm.get(c + ""); 
          hm.put(c + "", (j == null) ? 1 : j + 1);
        }

        hm = sortByValue(hm);
        
        System.out.println("---------");
        System.out.println("Letra Freq.");
        for (Map.Entry<String, Integer> en : hm.entrySet()) { 
            System.out.println("Key = " + en.getKey() +  
                          ", Value = " + en.getValue()); 
        } 

        Integer totalFreq = getTotalFrequency(hm);
        System.out.println("---------");
        System.out.println("TOTAL: " + totalFreq);
        System.out.println("TOTAL/2: " + totalFreq/2);
    } 
} 

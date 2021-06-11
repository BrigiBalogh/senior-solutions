package ipaddress;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ipv6 {

    private List<String> ipAddresses;


    public void readFromFile() {
        ipAddresses = null;
       try( Stream<String>s = Files.lines(Paths.get("ip.txt"))){
           ipAddresses = s.collect(Collectors.toList());

       }catch(IOException ioe) {
           throw new IllegalStateException("Cannot read file",ioe);
       }
    }

    public void countRows() {  //2
        System.out.print(ipAddresses.stream()
                .count());
    }

    public void foundMinIp() {  // 3
        System.out.println(ipAddresses.stream()
        .min(Comparator.naturalOrder()));
    }

    private String grouping(String ip) {
        return ip.startsWith("2001:0db8") ?
                    "documentation" :
                    (ip.startsWith("2001:0e") ?
                            "global unique" :
                            "local unique");
    }


    public void countIpByType() { //4
        Map<String,Long> ipTypes = ipAddresses.stream()
                .collect(Collectors.groupingBy(
                        this::grouping, Collectors.counting()));
    }

    public void filterdByIpAddress() throws IOException { // 5
        try (PrintWriter pw = new PrintWriter(new FileWriter("sok.txt"))) {
            ipAddresses.stream()
                    .filter(s -> s.chars()
                            .filter(c -> c == '0')
                            .count() > 18)
                    //  .collect(Collectors.toList())
                    /*.reduce(new PrintWriter(new FileWriter("sok.txt")),
                            (pw, s) -> { pw.println(s); return pw; },
                            (pw1, pw2) -> pw1); -- lezárja a fájlt? */
                    .forEach(pw::println);
        }
    }

    public void filteredByAddress2() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter("sok.txt"))) {
            IntStream.range(0, ipAddresses.size())
                    .mapToObj(i -> new Ip(ipAddresses.get(i), i+1))
                    .filter(ip -> ip.getIpName().chars()
                            .filter(c -> c == '0')
                            .count() > 18)
                    .forEach(ip -> pw.println(ip.getOrdinalNumber()+" "+ip.getIpName()));
        }
    }

    public void findIpByNumber()  {  // 6

        String ipv6 = "2001:0ebd:00ee:0003:0000:1234:5000:3400";
        String target = "2001:ebd:ee:3:0:1234:5000:3400";
        String trimmeldIp = Arrays.stream(ipv6.split(":"))
                .map(Ipv6::removeFirstZero)
                .map(Ipv6::removeFirstZero)
                .map(Ipv6::removeFirstZero)
                .collect(Collectors.joining(":"));
        System.out.println(trimmeldIp);
    }

    private static String removeFirstZero(String s) {
        return s.startsWith("0") ? s.substring(1) : s;
    }


    public void shortIp(String ip)
    {
        /*Fel splitelni és ahol 4 db nulla van egymás mellett , kicserélni 1db nullára. */
    }

}

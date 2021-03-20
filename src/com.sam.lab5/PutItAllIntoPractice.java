package com.sam.lab5;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PutItAllIntoPractice {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
//        Find all transactions in the year 2011 and sort them by value (small to high)
        findAll2011Transactions(transactions);
//        What are all the unique cities where the traders work

        listDistinctTraderCities(transactions);

//       Find all traders from Cambridge and sort them by name

        getCampridgeTraders(transactions);

//        Return a string of all traders’ names sorted alphabetically
        listAllTrandersNamesSorted(transactions);

//        Are any traders based in Milan
        isAnyTrandersInMilan(transactions);

//        Prints all transactions’ values from the traders living in Cambridge
        printAllMilanTransactions(transactions);
//        What’s the highest value of all the transactions
        getMaxTransaction(transactions);
//        What’s the highest value of all the transactions
        getMinTransaction(transactions);
    }

    private static void getMinTransaction(List<Transaction> transactions) {
        Optional<Transaction> minTransaction = transactions.stream().reduce((t1, t2)->t1.getValue()<t2.getValue()?t1:t2);
        minTransaction.ifPresent(d-> System.out.println("Min Transaction "+d.getValue()));

        Optional<Transaction> minTransactionShort =  transactions.stream().min(comparing(Transaction::getValue));
        minTransactionShort.ifPresent(t-> System.out.println(t.getValue()));
    }

    private static void getMaxTransaction(List<Transaction> transactions) {
        Optional<Integer> maxTransaction = transactions.stream().map(d->d.getValue()).reduce(Integer::max);
        maxTransaction.ifPresent(d-> System.out.println("max is : " + d));

        transactions.stream().max(comparing(Transaction::getValue)).ifPresent(d-> System.out.println(d.getValue()));
    }

    private static void printAllMilanTransactions(List<Transaction> transactions) {
        transactions.stream().filter(tr->tr.getTrader().getCity()
                                   .equalsIgnoreCase("cambridge"))
                                   .map(d->d.getValue()).forEach(System.out::println);
    }

    private static void isAnyTrandersInMilan(List<Transaction> transactions) {
        Optional<Transaction> milanTraderExist =     transactions.stream().filter(t->t.getTrader().getCity().equalsIgnoreCase("milan")).findAny();

        boolean milanBased= transactions.stream().anyMatch(transaction -> transaction.getTrader()
                                                          .getCity().equalsIgnoreCase("Milan"));
        milanTraderExist.ifPresent(d-> System.out.println("exist"));
    }

    private static void listAllTrandersNamesSorted(List<Transaction> transactions) {
        List<String>tradersNames = transactions
                               .stream().map(t->t.getTrader().getName())
                             .distinct()
                            .sorted(String::compareTo).collect(toList());
        String singleName = tradersNames.stream().reduce("",(n1,n2)->n1+n2);
        System.out.println(tradersNames);
        System.out.println(singleName);
    }

    private static void findAll2011Transactions(List<Transaction> transactions) {
        List<Transaction> tr2011 =  transactions.stream().filter(t->t.getYear()==2011).sorted(comparing(Transaction::getValue)).collect(toList());
        System.out.println(tr2011);
    }

    private static void listDistinctTraderCities(List<Transaction> transactions) {

        List<String> cities = transactions.stream().map(t->t.getTrader().getCity()).distinct().collect(Collectors.toUnmodifiableList());
        System.out.println(cities);
    }

    private static void getCampridgeTraders(List<Transaction> transactions) {
        List<Trader> campTraders = transactions.stream()
                                     .map(t->t.getTrader())
                                     .distinct()
                                     .filter(trader->trader.getCity()
                                     .equals("Cambridge"))
                                     .sorted(comparing(Trader::getName)).collect(toList());

        System.out.println(campTraders);
    }
}

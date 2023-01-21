package jp.ac.uryukyu.ie.e225706;

import java.util.*;

abstract class Character{
    private String name;
    private int win;
    private int lose;

    private ArrayList<Hand> hands = new ArrayList<>();

    Character(String name, int win, int lose){
        this.name = name;
        this.win = win;
        this.lose = lose;
    }
    
    void addAction(Hand hand) {
        hands.add(hand);
    }

    void openStatus(){
        System.out.printf("%s:win %d  lose %d\n", name, win, lose);
    }

    abstract void act(ArrayList<Character> targets);

    String getName(){
        return this.name;
    }
    int getWin(){
        return this.win;
    }
    int getLose(){
        return this.lose;
    }

    ArrayList<Hand> getHands(){
        return hands;
    }

    void setWin(int win){
        this.win = win;
    }
    void setLose(int lose){
        this.lose = lose;
    }
}

class Player extends Character{

    Player(String name, int win, int lose){
        super(name, win, lose);
    }

    @Override
    void act(ArrayList<Character> targets) {
        var command_selector = new CommandSelector();
        
       //選択肢を用意する
        for(var action: getHands()) {
            command_selector.addCommand(action.name());
        }
       //ユーザの選択を待つ
        var command_number = command_selector.waitForUsersCommand("最初はグー、ジャンケン...");
        System.out.println("ポンッ！");
        getHands().get(command_number);
    }
}

 interface Hand {
    String name();
    int eigenvalue();
 }

 class CommandSelector {
    ArrayList<String> commands;
    Scanner scanner;

    CommandSelector() {
        scanner = new Scanner(System.in);
        commands = new ArrayList<>();
    }

    void addCommand(String command_name) {
        commands.add(command_name);
    }

    //promptを表示した上で，ユーザの選択を待つ
    int waitForUsersCommand(String prompt) {
        var index = 0;
        System.out.println(prompt);
        for(var command : commands) { //選択肢をprint
            System.out.println(index + ":" + command);
            index += 1;
        }

        //標準入力から数値を入力
        while(true) {
            int target_index = scanner.nextInt();

            if (target_index >= 0 && target_index < commands.size()) {
                return target_index;
            }
        }        
    }
}
class Enemy extends Character {
    Enemy(String name, int win, int lose) {
        super(name, win, lose);
    }
 
    @Override
    void act(ArrayList<Character> targets) {
        Random random = new Random();
        int command = random.nextInt(3);
        getHands().get(command);
    }
 }

 class Rock implements Hand{
    private String name;
    private int eigenvalue;

    Rock(String name, int eigenvalue){
        this.name = name;
        this.eigenvalue = eigenvalue;
    }

    @Override
    public String name(){
        return name;
    }

    @Override
    public int eigenvalue(){
        return this.eigenvalue;
    }
 }

 class scissors implements Hand{
    private String name;
    private int eigenvalue;

    scissors(String name, int eigenvalue){
        this.name = name;
        this.eigenvalue = eigenvalue;
    }

    @Override
    public String name(){
        return name;
    }

    @Override
    public int eigenvalue(){
        return this.eigenvalue;
    }
 }

 class paper implements Hand{
    private String name;
    private int eigenvalue;

    paper(String name, int eigenvalue){
        this.name = name;
        this.eigenvalue = eigenvalue;
    }

    @Override
    public String name(){
        return name;
    }

    @Override
    public int eigenvalue(){
        return this.eigenvalue;
    }
 }

 class Gameboard{
    ArrayList<Character> order = new ArrayList<>();

    Gameboard() {
        var Rohan = new Player("あなた", 0, 0);
        Rohan.addAction(new Rock("グー", 1));
        Rohan.addAction(new paper("チョキ", 2));
        Rohan.addAction(new paper("パー", 3));

        var Honda = new Enemy("本田圭佑", 0, 0);
        Honda.addAction(new Rock("グー", 1));
        Honda.addAction(new paper("チョキ", 2));
        Honda.addAction(new paper("パー", 3));

        order.add(Rohan);
        order.add(Honda);
    }
 }
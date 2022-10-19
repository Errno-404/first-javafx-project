package agh.ics.oop;


public class World {
    public static void main(String[] arg) {
        System.out.println("system wystartował");
        Direction[] pet = convert(arg);
        run(pet);
        System.out.println("system zakończył działanie");
    }

    public static Direction[] convert(String[] args){
        int n = args.length;
        Direction[] ans = new Direction[n];
        Direction converted;

        for(int i = 0; i < n; i ++){
            switch (args[i]) {
                case "f" -> converted = Direction.Forward;
                case "b" -> converted = Direction.Backward;
                case "l" -> converted = Direction.Left;
                case "r" -> converted = Direction.Right;
                default -> converted = Direction.None;
            }

            ans[i] = converted;
        }
        return ans;
    }
    public static void run(Direction[] pet) {
        String message;
        for (Direction arg : pet) {
            message = switch (arg) {
                case Forward -> "idzie do przodu";
                case Backward -> "idzie do tyłu";
                case Right -> "skręca w prawo";
                case Left -> "skręca w lewo";
                default -> "";
            };


            if (!message.equals("")) {
                System.out.println("Zwierzak " + message);
            }
        }
    }


//    public static void run(String[] args) {
//        int n = args.length;
//        String message;
//        for (String arg : args) {
//            message = switch (arg) {
//                case "f" -> "idzie do przodu";
//                case "b" -> "idzie do tyłu";
//                case "r" -> "skręca w prawo";
//                case "l" -> "skręca w lewo";
//                default -> "";
//            };
//
//
//            if (!message.equals("")) {
//                System.out.println("Zwierzak " + message);
//            }
//        }
//
//
//        System.out.println("Zwierzak idzie do przodu");
//        for(int i = 0; i < n - 1; i++){
//            System.out.print(args[i] + ", ");
//        }
//
//        // if jest użyty w celu ładniejszego formatowania
//        // (brak nowej linii w przypadku braku argumentów)
//        if(args.length > 0) {
//            System.out.print(args[n - 1] + '\n');
//        }
//    }
}

public class UML {
    private StringBuilder GenInput = new StringBuilder("@startuml\n skinparam classAttributeIconSize 0\n");
    public void addInput(String inputs) {
        GenInput.append(inputs);
    }

   
    public StringBuilder getInput() {
        return GenInput;
    }

}
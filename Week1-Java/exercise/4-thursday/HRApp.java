public class HRApp {
    public static void main(String[] args) {
        EmployeeProfile e = new EmployeeProfile("EMP-101", "Surya Solaiappan", 5000.0);

        System.out.println("Initial Salary: $" + e.getMonthlySalary());

        e.setMonthlySalary(-2);
        e.setName(null);

        System.out.println("Final Name: " + e.getName());
        System.out.println("Final Salary: $" + e.getMonthlySalary());
    }
}
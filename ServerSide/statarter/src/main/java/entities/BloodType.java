package entities;

public enum BloodType {
Apos("A+"),Aneg("A-"),Bpos("B+"),Bneg("B-"),ABpos("AB+"),ABneg("AB-")
,Opos("O+"),Oneg("O-"),Unknowen("UN");

private final String label;

private BloodType(String label) { this.label = label; }

@Override
public String toString() { return label; }
}

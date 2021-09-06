package main.test;
import java.util.Objects;
import main.test.LockProfile.LockProfileEnum;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        String podName = "eugx";
        String lockProfile = "xxxecr";


        LockProfile profile = new LockProfile();
        profile.setLockProfile(LockProfile.LockProfileEnum.fromValue("ACCESS"));

        LockProfile profile1= new LockProfile();
        profile1.setLockProfile(LockProfile.LockProfileEnum.fromValue("ACCESS"));

        if(Objects.isNull(profile.getLockProfile()) || !LockProfile.LockProfileEnum.ACCESS.toString().equals(profile.getLockProfile().toString())){
            System.out.println("working ....");
        }

        String temp = "ACCESS";
        System.out.println(profile.getLockProfile() == LockProfileEnum.ACCESS);
        System.out.println(">>>>>>> " + profile.getLockProfile().name());

    }
}
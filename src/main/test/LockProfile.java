package main.test;

import java.util.Objects;

public class LockProfile   {
    /**
     * Gets or Sets lockProfile
     */
    public enum LockProfileEnum {
        UPGRADE("UPGRADE"),

        ACCESS("ACCESS");

        private String value;

        LockProfileEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static LockProfileEnum fromValue(String text) {
            for (LockProfileEnum b : LockProfileEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    private LockProfileEnum lockProfile = null;

    public LockProfile lockProfile(LockProfileEnum lockProfile) {
        this.lockProfile = lockProfile;
        return this;
    }

    public LockProfileEnum getLockProfile() {
        return lockProfile;
    }

    public void setLockProfile(LockProfileEnum lockProfile) {
        this.lockProfile = lockProfile;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LockProfile lockProfile = (LockProfile) o;
        return Objects.equals(this.lockProfile, lockProfile.lockProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lockProfile);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LockProfile {\n");

        sb.append("    lockProfile: ").append(toIndentedString(lockProfile)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

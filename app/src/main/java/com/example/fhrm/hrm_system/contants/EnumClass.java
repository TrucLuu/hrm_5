package com.example.fhrm.hrm_system.contants;

/**
 * Created by luuhoangtruc on 27/01/2016.
 */
public class EnumClass {

    public static final int TRAINEE_CODE = 0;
    public static final int INTERNSHIP_CODE = 1;
    public static final int OFFICIAL_STAFF_CODE = 2;
    public static final int MAX_STATUS_CODE = 3;

    public static final int MANAGER_CODE = 0;
    public static final int VPG_CODE = 1;
    public static final int LEADER_CODE = 2;
    public static final int QA_CODE = 3;
    public static final int STAFF_CODE = 4;
    public static final int TESTER_CODE = 5;
    public static final int COMTOR_CODE = 6;
    public static final int BRIDGE_SE_CODE = 7;
    public static final int MAX_POI_CODE = 8;

    public enum StatusCode {

        TRAINEE(TRAINEE_CODE, "Trainee"), INTERNSHIP(INTERNSHIP_CODE, "InternShip"), OFFICIAL_STAFF(OFFICIAL_STAFF_CODE, "Official Staff"), UNKNOW(MAX_STATUS_CODE, "Unknown");

        private final int value;
        private final String text;

        private StatusCode(int value, String text) {
            this.value = value;
            this.text = text;
        }

        public static StatusCode fromValue(final int value) {
            for (StatusCode code : StatusCode.values()) {
                if (value == code.value && value < MAX_STATUS_CODE)
                    return code;
            }
            return UNKNOW;
        }

        public int getValue() {
            return value;
        }

        public String getText() {
            return text;
        }
    }

    public enum PositionCode {

        MANAGER(MANAGER_CODE, "Manager"), VPG(VPG_CODE, "Group Leader"), LEADER(LEADER_CODE, "Leader"),
        QA(QA_CODE, "Quality Assurance"), STAFF(STAFF_CODE, "Staff"), TESTER(TESTER_CODE, "Tester"),
        COMTOR(COMTOR_CODE, "Comtor"), BRIDGE_SE(BRIDGE_SE_CODE, "Bridge System Engineer"),
        UNKNOWPOSITION(MAX_POI_CODE, "Unknown Position");

        private final int value;
        private final String text;

        private PositionCode(int value, String text) {
            this.value = value;
            this.text = text;
        }

        public static PositionCode fromPositionValue(final int value) {
            for (PositionCode code : PositionCode.values()) {
                if (value == code.value && value < MAX_POI_CODE)
                    return code;
            }
            return UNKNOWPOSITION;
        }

        public int getValue() {
            return value;
        }

        public String getText() {
            return text;
        }
    }
}

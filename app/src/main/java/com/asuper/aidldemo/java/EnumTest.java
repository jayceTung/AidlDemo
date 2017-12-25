package com.asuper.aidldemo.java;

/**
 * Created by super on 2017/8/1.
 */

public enum EnumTest {
    FILE_NOT_EXIST(100), FILE_EXIT(200);

    private int code;

    public int getCode() {
        return code;
    }

    EnumTest(int code) {
        this.code = code;
    }

    public static void main(String[] args) {
        for (EnumTest name : EnumTest.values()) {
            System.out.println(name.getCode());
        }
        System.out.println(EnumTest.FILE_EXIT.getDeclaringClass()
                + "" + EnumTest.FILE_NOT_EXIST.getDeclaringClass());

        for (int i = 0; i < 10; i++) {
            System.out.println("isodd  =" + isOdd(i));
        }
        float i = 10000;
        int j = 33;
        float k = (float) j;
        float percent = (1 - j / i) * 100;
        String mDefent = String.format("%.2f", percent);
        System.out.println("isodd  =" + mDefent);


//        String str = "{\n" +
//                "    \"msgTag\": 10030203,\n" +
//                "    \"time\": 10,\n" +
//                "    \"round\": 1,\n" +
//                "    \"total\": 12,\n" +
//                "    \"topicId\": 1,\n" +
//                "    \"topicContent\": \"题目\",\n" +
//                "    \"options\": [\n" +
//                "        {\n" +
//                "            \"optionId\": 1,\n" +
//                "            \"optionContent\": \"题目\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"optionId\": 2,\n" +
//                "            \"optionContent\": \"题目\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"optionId\": 3,\n" +
//                "            \"optionContent\": \"题目\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"optionId\": 4,\n" +
//                "            \"optionContent\": \"题目\"\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";
//        Question que = GsonUtil.GsonToBean(str, Question.class);
//
//        mLogicControl = new AnswerLogicControl(AnswerRoom.this, mPopRoot);
//
//        mLogicControl.onStartAnswer(que);



//        String str = "{\n" +
//                "    \"MsgTag\": 10030204,\n" +
//                "    \"isRevive\": false,\n" +
//                "    \"selectOptionId\": 3,\n" +
//                "    \"correctId\": 2,\n" +
//                "    \"round\": 1,\n" +
//                "    \"total\": 12,\n" +
//                "    \"topicId\": 1,\n" +
//                "    \"topicContent\": \"题目\",\n" +
//                "    \"analysis\": \"blabla\",\n" +
//                "    \"options\": [\n" +
//                "        {\n" +
//                "            \"optionId\": 1,\n" +
//                "            \"optionContent\": \"选项A\",\n" +
//                "            \"selectCount\": 300\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"optionId\": 1,\n" +
//                "            \"optionContent\": \"选项A\",\n" +
//                "            \"selectCount\": 300\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"optionId\": 1,\n" +
//                "            \"optionContent\": \"选项A\",\n" +
//                "            \"selectCount\": 300\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"optionId\": 1,\n" +
//                "            \"optionContent\": \"选项A\",\n" +
//                "            \"selectCount\": 300\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";
//        Question que = GsonUtil.GsonToBean(str, Question.class);
//
//        mLogicControl = new AnswerLogicControl(AnswerRoom.this, mPopRoot);
//
//        mLogicControl.onResultShow(que, false, 1, 1);
    }

    public static boolean isOdd(int a) {
        if((a & 1) != 1){
            return true;
        }
        return false;
    }
}

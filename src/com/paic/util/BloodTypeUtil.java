package com.paic.util;

import com.paic.dto.BloodTypeDto;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Admin
 * @Date: 2021/8/29 12:02
 * @Description: 血型判断工具类
 */
public class BloodTypeUtil {
    private static final String AA = "AA";
    private static final String AO = "AO";
    private static final String BB = "BB";
    private static final String BO = "BO";
    private static final String AB = "AB";
    private static final String OO = "OO";
    private static final String A = "A";
    private static final String B = "B";
    private static final String O = "O";

    private static final String OA = "OA";
    private static final String OB = "OB";
    private static final String BA = "BA";

    private static List<String> aList = new ArrayList<>();  //A型血
    private static List<String> bList = new ArrayList<>();  //B型血
    private static List<String> abList = new ArrayList<>(); //AB型血
    private static List<String> oList = new ArrayList<>();  //O型血

    private static List<String> allList = new ArrayList<>();  //O型血

    private static List<String> allAList = new ArrayList<>();
    private static List<String> allBList = new ArrayList<>();
    private static List<String> allABList = new ArrayList<>();

    static {
        aList.add(AA);
        aList.add(AO);

        bList.add(BB);
        bList.add(BO);

        abList.add(AB);

        oList.add(OO);

        allList.add(A);
        allList.add(B);
        allList.add(AB);
        allList.add(O);

        allAList.addAll(aList);
        allAList.add(OA);

        allBList.addAll(bList);
        allBList.add(OB);

        allABList.addAll(abList);
        allABList.add(BA);
    }

    //根据父母血型判断子女可能的血型及比例，父母血型可以互换。入参解释：如果是A型，可能是AA，也可能是AO
    public static List<BloodTypeDto> generateBloodType(String fatherType, String motherType) throws Exception {
        if (!allList.contains(fatherType)||!allList.contains(motherType)) {
            throw new Exception("入参传入有误..");
        }

        List<String> list = new ArrayList<>();
        for (String faType : allList) {
            for (String moType : allList) {
                if (faType.equals(fatherType)&&motherType.equals(moType)) {
                    List<String> faList = getSubclassByCategory(fatherType);
                    List<String> moList = getSubclassByCategory(moType);
                    generateSubList(faList, moList, list);
                }
            }
        }

        return getResultList(list);
    }

    private static List<BloodTypeDto> getResultList(List<String> list) {
        List<BloodTypeDto> resultList = new ArrayList<>();

        int countA = 0;
        int countB = 0;
        int countAB = 0;
        int countO = 0;
        for (String type : list) {
            if (allAList.contains(type)) {
                countA++;
            } else if (allBList.contains(type)) {
                countB++;
            } else if (allABList.contains(type)) {
                countAB++;
            } else {
                countO++;
            }
        }

        BloodTypeDto dtoA = new BloodTypeDto();
        dtoA.setBloodType(A);
        dtoA.setProportion(countA);
        resultList.add(dtoA);

        BloodTypeDto dtoB = new BloodTypeDto();
        dtoB.setBloodType(B);
        dtoB.setProportion(countB);
        resultList.add(dtoB);

        BloodTypeDto dtoAB = new BloodTypeDto();
        dtoAB.setBloodType(AB);
        dtoAB.setProportion(countAB);
        resultList.add(dtoAB);

        BloodTypeDto dtoO = new BloodTypeDto();
        dtoO.setBloodType(O);
        dtoO.setProportion(countO);
        resultList.add(dtoO);

        return resultList;
    }

    private static void generateSubList(List<String> faList, List<String> moList, List<String> list) throws Exception {
        for (String subFa : faList) {
            for (String subMo : moList) {
                List<String> subFaList = getSubclass2ByCategory2(subFa);
                List<String> subMoList = getSubclass2ByCategory2(subMo);
                generateSubList2(subFaList, subMoList, list);
            }
        }
    }

    private static void generateSubList2(List<String> subFaList, List<String> subMoList, List<String> list) {
        for (String subFa2 : subFaList) {
            for (String subMo2 : subMoList) {
                String type = subFa2+subMo2;
                list.add(type);
            }
        }
    }

    //根据血型大类获取对应的小类
    private static List<String> getSubclassByCategory(String category) throws Exception {
        switch (category) {
            case A :
                return aList;
            case B :
                return bList;
            case AB :
                return abList;
            case O :
                return oList;
            default:
                throw new Exception("血型大类传入有误");
        }
    }

    //根据子类获取更小的小类，例如：传入AA则输出A， A
    private static List<String> getSubclass2ByCategory2(String category2) throws Exception {
        List<String> list = new ArrayList<>();

        switch (category2) {
            case AA :
                list.add(A);
                list.add(A);
                return list;
            case AO :
                list.add(A);
                list.add(O);
                return list;

            case BB :
                list.add(B);
                list.add(B);
                return list;

            case BO :
                list.add(B);
                list.add(O);
                return list;

            case AB :
                list.add(A);
                list.add(B);
                return list;

            case OO :
                list.add(O);
                list.add(O);
                return list;
            default:
                throw new Exception("血型小类传入有误");
        }
    }

    public static void main(String[] args) throws Exception {
        List<BloodTypeDto> bloodTypeDtoList = generateBloodType(AB, AB);
        for (BloodTypeDto dto : bloodTypeDtoList) {
            System.out.println(dto);
        }
    }
}

package com.example.shizhan.reggie.Test;
import java.util.ArrayList;
import java.util.regex.*;

public class Zifu {
    public static void main( String[] args ){

        // 按指定模式在字符串查找
        String line = "113.542815,34.826075;113.542756,34.82608;113.542418,34.826086\",\"action\":\"右转\",\"assistant_action\":[],\"tmcs\":[{\"lcode\":[],\"distance\":\"5\",\"status\":\"未知\",\"polyline\":\"113.542815,34.826075;113.542756,34.82608\"},{\"lcode\":[],\"distance\":\"30\",\"status\":\"未知\",\"polyline\":\"113.542756,34.82608;113.542418,34.826086\"}],\"cities\":[{\"name\":\"郑州市\",\"citycode\":\"0371\",\"adcode\":\"410100\",\"districts\":[{\"name\":\"中原区\",\"adcode\":\"410102\"}]}]},{\"instruction\":\"沿长椿路向北行驶116米左转调头\",\"orientation\":\"北\",\"road\":\"长椿路\",\"distance\":\"116\",\"tolls\":\"0\",\"toll_distance\":\"0\",\"toll_road\":[],\"duration\":\"100\",\"polyline\":\"113.542418,34.826086;113.542461,34.82666;113.542499,34.827137\",\"action\":\"左转调头\",\"assistant_action\":[],\"tmcs\":[{\"lcode\":[],\"distance\":\"116\",\"status\":\"畅通\",\"polyline\":\"113.542418,34.826086;113.542461,34.82666;113.542499,34.827137\"}],\"cities\":[{\"name\":\"郑州市\",\"citycode\":\"0371\",\"adcode\":\"410100\",\"districts\":[{\"name\":\"中原区\",\"adcode\":\"410102\"}]}]},{\"instruction\":\"沿长椿路向南行驶1.1千米左转进入主路\",\"orientation\":\"南\",\"road\":\"长椿路\",\"distance\":\"1118\",\"tolls\":\"0\",\"toll_distance\":\"0\",\"toll_road\":[],\"duration\":\"168\",\"polyline\":\"113.542172,34.827137;113.54215,34.82666;113.541979,34.822566;113.541925,34.821107;113.541748,34.817052\",\"action\":\"左转\",\"assistant_action\":\"进入主路\",\"tmcs\":[{\"lcode\":[],\"distance\":\"507\",\"status\":\"畅通\",\"polyline\":\"113.542172,34.827137;113.54215,34.82666;113.541979,34.822566\"},{\"lcode\":[],\"distance\":\"161\",\"status\":\"畅通\",\"polyline\":\"113.541979,34.822566;113.541925,34.821107\"},{\"lcode\":[],\"distance\":\"450\",\"status\":\"畅通\",\"polyline\":\"113.541925,34.821107;113.541748,34.817052\"}],\"cities\":[{\"name\":\"郑州市\",\"citycode\":\"0371\",\"adcode\":\"410100\",\"districts\":[{\"name\":\"中原区\",\"adcode\":\"410102\"}]}]},{\"instruction\":\"沿翠竹街向东行驶1.5千米向右前方行驶进入辅路\",\"orientation\":\"东\",\"road\":\"翠竹街\",\"distance\":\"1529\",\"tolls\":\"0\",\"toll_distance\":\"0\",\"toll_road\":[],\"duration\":\"157\",\"polyline\":\"113.541968,34.816907;113.546281,34.816928;113.551468,34.816966;113.552901,34.816966;113.553051,34.816966;113.554687,34.816966;113.555792,34.816961;113.555899,34.816961;113.556259,34.816945;113.557208,34.81695;113.558721,34.816945\",\"action\":\"向右前方行驶\",\"assistant_action\":\"进入辅路\",\"tmcs\":[{\"lcode\":[],\"distance\":\"394\",\"status\":\"畅通\",\"polyline\":\"113.541968,34.816907;113.546281,34.816928\"},{\"lcode\":[],\"distance\":\"474\",\"status\":\"畅通\",\"polyline\":\"113.546281,34.816928;113.551468,34.816966\"},{\"lcode\":[],\"distance\":\"131\",\"status\":\"畅通\",\"polyline\":\"113.551468,34.816966;113.552901,34.816966\"},{\"lcode\":[],\"distance\":\"13\",\"status\":\"畅通\",\"polyline\":\"113.552901,34.816966;113.553051,34.816966\"},{\"lcode\":[],\"distance\":\"260\",\"status\":\"畅通\",\"polyline\":\"113.553051,34.816966;113.554687,34.816966;113.555792,34.816961;113.555899,34.816961\"},{\"lcode\":[],\"distance\":\"33\",\"status\":\"畅通\",\"polyline\":\"113.555899,34.816961;113.556259,34.816945\"},{\"lcode\":[],\"distance\":\"86\",\"status\":\"畅通\",\"polyline\":\"113.556259,34.816945;113.557208,34.81695\"},{\"lcode\":[],\"distance\":\"138\",\"status\":\"畅通\",\"polyline\":\"113.557208,34.81695;113.558721,34.816945\"}],\"cities\":[{\"name\":\"郑州市\",\"citycode\":\"0371\",\"adcode\":\"410100\",\"districts\":[{\"name\":\"中原区\",\"adcode\":\"410102\"}]}]},{\"instruction\":\"向东行驶84米到达目的地\",\"orientation\":\"东\",\"distance\":\"84\",\"tolls\":\"0\",\"toll_distance\":\"0\",\"toll_road\":[],\"duration\":\"61\",\"polyline\":\"113.558721,34.816923;113.55964,34.816906\",\"action\":[],\"assistant_action\":\"到达目的地\"";
        String pattern = "\\d{2,}\\.\\d{2,}";
        //String pattern = "\\d+\\.\\d+\\,\\d+\\.\\d";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        //
        String s = "jsonp_499741_1659630168800_({\"status\":\"1\",\"info\":\"OK\",\"infocode\":\"10000\",\"count\":\"1\",\"route\":{\"origin\":\"113.550087,34.827019\",\"destination\":\"113.554658,34.819376\",\"taxi_cost\":\"10\",\"paths\":[{\"distance\":\"1232\",\"duration\":\"245\",\"strategy\":\"速度最快\",\"tolls\":\"0\",\"toll_distance\":\"0\",\"steps\":[{\"instruction\":\"沿莲花街向东行驶115米右转\",\"orientation\":\"东\",\"road\":\"莲花街\",\"distance\":\"115\",\"tolls\":\"0\",\"toll_distance\":\"0\",\"toll_road\":[],\"duration\":\"21\",\"polyline\":\"113.550086,34.827021;113.55135,34.827008\",\"action\":\"右转\",\"assistant_action\":[],\"tmcs\":[{\"lcode\":[],\"distance\":\"115\",\"status\":\"畅通\",\"polyline\":\"113.550086,34.827021;113.55135,34.827008\"}],\"cities\":[{\"name\":\"郑州市\",\"citycode\":\"0371\",\"adcode\":\"410100\",\"districts\":[{\"name\":\"中原区\",\"adcode\":\"410102\"}]}]},{\"instruction\":\"沿石楠路向南行驶826米左转\",\"orientation\":\"南\",\"road\":\"石楠路\",\"distance\":\"826\",\"tolls\":\"0\",\"toll_distance\":\"0\",\"toll_road\":[],\"duration\":\"167\",\"polyline\":\"113.551404,34.826912;113.551409,34.82666;113.55142,34.825994;113.551415,34.824803;113.551431,34.823671;113.551431,34.8235;113.551441,34.823296;113.551441,34.823087;113.551441,34.822105;113.551447,34.821392;113.551452,34.820775;113.551452,34.820705;113.551452,34.820201;113.551458,34.819407\",\"action\":\"左转\",\"assistant_action\":[],\"tmcs\":[{\"lcode\":[],\"distance\":\"101\",\"status\":\"畅通\",\"polyline\":\"113.551404,34.826912;113.551409,34.82666;113.55142,34.825994\"},{\"lcode\":[],\"distance\":\"131\",\"status\":\"畅通\",\"polyline\":\"113.55142,34.825994;113.551415,34.824803\"},{\"lcode\":[],\"distance\":\"126\",\"status\":\"畅通\",\"polyline\":\"113.551415,34.824803;113.551431,34.823671\"},{\"lcode\":[],\"distance\":\"19\",\"status\":\"畅通\",\"polyline\":\"113.551431,34.823671;113.551431,34.8235\"},{\"lcode\":[],\"distance\":\"22\",\"status\":\"畅通\",\"polyline\":\"113.551431,34.8235;113.551441,34.823296\"},{\"lcode\":[],\"distance\":\"22\",\"status\":\"畅通\",\"polyline\":\"113.551441,34.823296;113.551441,34.823087\"},{\"lcode\":[],\"distance\":\"108\",\"status\":\"畅通\",\"polyline\":\"113.551441,34.823087;113.551441,34.822105\"},{\"lcode\":[],\"distance\":\"79\",\"status\":\"畅通\",\"polyline\":\"113.551441,34.822105;113.551447,34.821392\"},{\"lcode\":[],\"distance\":\"68\",\"status\":\"畅通\",\"polyline\":\"113.551447,34.821392;113.551452,34.820775\"},{\"lcode\":[],\"distance\":\"7\",\"status\":\"畅通\",\"polyline\":\"113.551452,34.820775;113.551452,34.820705\"},{\"lcode\":[],\"distance\":\"55\",\"status\":\"畅通\",\"polyline\":\"113.551452,34.820705;113.551452,34.820201\"},{\"lcode\":[],\"distance\":\"88\",\"status\":\"畅通\",\"polyline\":\"113.551452,34.820201;113.551458,34.819407\"}],\"cities\":[{\"name\":\"郑州市\",\"citycode\":\"0371\",\"adcode\":\"410100\",\"districts\":[{\"name\":\"中原区\",\"adcode\":\"410102\"}]}]},{\"instruction\":\"沿迎春街向东行驶291米到达目的地\",\"orientation\":\"东\",\"road\":\"迎春街\",\"distance\":\"291\",\"tolls\":\"0\",\"toll_distance\":\"0\",\"toll_road\":[],\"duration\":\"57\",\"polyline\":\"113.551452,34.819359;113.553335,34.819369;113.554145,34.819375;113.554177,34.819375;113.554445,34.819375;113.554657,34.81937\",\"action\":[],\"assistant_action\":\"到达目的地\",\"tmcs\":[{\"lcode\":[],\"distance\":\"171\",\"status\":\"畅通\",\"polyline\":\"113.551452,34.819359;113.553335,34.819369\"},{\"lcode\":[],\"distance\":\"77\",\"status\":\"畅通\",\"polyline\":\"113.553335,34.819369;113.554145,34.819375;113.554177,34.819375\"},{\"lcode\":[],\"distance\":\"24\",\"status\":\"畅通\",\"polyline\":\"113.554177,34.819375;113.554445,34.819375\"},{\"lcode\":[],\"distance\":\"19\",\"status\":\"畅通\",\"polyline\":\"113.554445,34.819375;113.554657,34.81937\"}],\"cities\":[{\"name\":\"郑州市\",\"citycode\":\"0371\",\"adcode\":\"410100\",\"districts\":[{\"name\":\"中原区\",\"adcode\":\"410102\"}]}]}],\"restriction\":\"0\",\"traffic_lights\":\"4\"}]}})";
        Matcher m = r.matcher(line);
        ArrayList<Double> arrX = new ArrayList<>();
        ArrayList<Double> arrY = new ArrayList<>();
        int II = 1;
        while (m.find( )) {
           System.out.println("Found value: " + m.group(0) );
           if(II%2==0){
               arrY.add(Double.parseDouble(m.group(0)));
           }else {
               arrX.add( Double.parseDouble(m.group(0)));
           }
           II++;
        }
        String s_X = "";
        s_X = "[";
        for(int i=0;i<arrX.size()-1;i++){
            s_X = s_X + arrX.get(i) + ",";
        }
        s_X = s_X + arrX.get(arrX.size()-1) + "]";
        System.out.println(s_X);

        System.out.println("Y坐标");
        String s_Y = "";
        s_Y = "[";
        for(int i=0;i<arrY.size()-1;i++){
            s_Y = s_Y + arrY.get(i) + ",";
        }
        s_Y = s_Y + arrY.get(arrY.size()-1) + "]";
        System.out.println(s_Y);
        double[][] lines = new double[arrX.size()][2];
        for(int i=0;i<arrX.size()-1;i++){
            lines[i][0] = arrX.get(i);
            lines[i][1] = arrY.get(i);
        }
        String lines_s = "[";
        for (int i = 0; i < arrX.size()-1; i++) {
           lines_s = lines_s +"["+ arrX.get(i)+","+arrY.get(i)+"],";
        }
        lines_s = lines_s + "["+arrX.get(arrX.size()-1)+","+arrY.get(arrY.size()-1)+"]]";
        System.out.println(lines_s);
    }

}

import java.util.*;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {
    public String getResult(String inputStr) {
        String[] arr = inputStr.split("\\s+");
        if (arr.length == 1) {
            return inputStr + " 1";
        } else {
            //split the input string with 1 to n pieces of spaces
            Map<String, List<Input>> map = formatDate(arr);
            List<Word> resultModel = buildResultModel(map);
            resultModel.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
            String resultView=buildResultView(resultModel);
            return resultView;
        }
    }

    Map<String, List<Input>> formatDate(String[] arr) {
        List<Input> inputList = new ArrayList<>();
        for (String s : arr) {
            Input input = new Input(s);
            inputList.add(input);
        }

        //get the map for the next step of sizing the same word
        return getListMap(inputList);
    }

    List<Word> buildResultModel(Map<String, List<Input>> map) {
        List<Word> list = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
            Word input = new Word(entry.getKey(), entry.getValue().size());
            list.add(input);
        }
        return list;
    }

    String buildResultView(List<Word> list) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Word w : list) {
            String s = w.getValue() + " " + w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}

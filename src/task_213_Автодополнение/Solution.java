package task_213_Автодополнение;

import java.io.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int result = processData(reader);
        PrintWriter writer = new PrintWriter("output.txt");
        writer.println(result);
        writer.close();
    }

    public static int processData(BufferedReader reader) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        String[] words = reader.readLine().split(" ");
        reader.close();
        return calculateLetterKeyCount(words);
    }

    private static int calculateLetterKeyCount(String[] words) {
        int keyCount = 0;
        AutocompleteTrie dictionary = new AutocompleteTrie();
        for (String word : words) {
            if (!dictionary.findWord(word)) {
                keyCount += word.length();
                dictionary.addWord(word);
            } else {
                keyCount += dictionary.getPrefixLength(word);
            }
        }
        return keyCount;
    }

    static class AutocompleteTrie {
        AutocompleteTrieNode root;

        public AutocompleteTrie() {
            this.root = new AutocompleteTrieNode('\0', false);
        }

        private boolean findWord(String word) {
            AutocompleteTrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (current.letters[ch - 'a'] == null) return false;
                current = current.letters[ch - 'a'];
            }
            return current.isEnd;
        }

        private void addWord(String word) {
            AutocompleteTrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (current.letters[ch - 'a'] == null)
                    current.letters[ch - 'a'] = new AutocompleteTrieNode(ch, (i == word.length() - 1));
                current.letters[ch - 'a'].wordCount++;
                if (i == word.length() - 1) current.letters[ch - 'a'].isEnd = true;
                else current = current.letters[ch - 'a'];
            }
        }

        private int getPrefixLength(String word) {
            AutocompleteTrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (current.wordCount == 1) return i;
                if (current.letters[ch - 'a'] == null) break;
                current = current.letters[ch - 'a'];
            }
            return word.length();
        }


        private static class AutocompleteTrieNode {
            AutocompleteTrieNode[] letters;
            char letter;
            boolean isEnd;
            int wordCount;

            public AutocompleteTrieNode(char letter, boolean isEnd) {
                this.letter = letter;
                this.isEnd = isEnd;
                this.wordCount = 0;
                this.letters = new AutocompleteTrieNode[26];
            }
        }
    }
}

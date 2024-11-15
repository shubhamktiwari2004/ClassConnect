package com.eduportal.core.ClassConnect.Teacher.teacherModel;

public class AddStudentModel {
        private String studentName;
        private int mathsMarks;
        private int scienceMarks;
        private int hindiMarks;
        private int englishMarks;
        private int totalMarks;

        // Getters and Setters
        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public int getMathsMarks() {
            return mathsMarks;
        }

        public void setMathsMarks(int mathsMarks) {
            this.mathsMarks = mathsMarks;
        }

        public int getScienceMarks() {
            return scienceMarks;
        }

        public void setScienceMarks(int scienceMarks) {
            this.scienceMarks = scienceMarks;
        }

        public int getHindiMarks() {
            return hindiMarks;
        }

        public void setHindiMarks(int hindiMarks) {
            this.hindiMarks = hindiMarks;
        }

        public int getEnglishMarks() {
            return englishMarks;
        }

        public void setEnglishMarks(int englishMarks) {
            this.englishMarks = englishMarks;
        }

        public int getTotalMarks() {
            return totalMarks;
        }

        public void setTotalMarks(int totalMarks) {
            this.totalMarks = totalMarks;
        }
}

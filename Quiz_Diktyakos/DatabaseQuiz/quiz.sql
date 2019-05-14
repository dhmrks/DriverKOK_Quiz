-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Sep 25, 2018 at 12:16 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quiz`
--

-- --------------------------------------------------------

--
-- Table structure for table `exams`
--

CREATE TABLE `exams` (
  `exam_id` int(2) NOT NULL,
  `exam_datetime` datetime NOT NULL,
  `exam_center_id` int(2) NOT NULL,
  `exam_status` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `exams`
--

INSERT INTO `exams` (`exam_id`, `exam_datetime`, `exam_center_id`, `exam_status`) VALUES
(1, '2018-03-09 20:00:00', 1, 1),
(2, '2018-03-20 18:00:00', 2, 1),
(3, '2018-09-20 15:00:00', 5, 0);

-- --------------------------------------------------------

--
-- Table structure for table `exam_centers`
--

CREATE TABLE `exam_centers` (
  `exam_center_id` int(2) NOT NULL,
  `exam_center_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `exam_center_addr` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `exam_centers`
--

INSERT INTO `exam_centers` (`exam_center_id`, `exam_center_name`, `exam_center_addr`, `user_id`) VALUES
(1, 'Σεπόλια', 'Δράμας 130', 2),
(2, 'Γαλάτσι', 'Δωδώνης 13', 2),
(5, 'STEF', 'aigaleou 34', 2);

-- --------------------------------------------------------

--
-- Table structure for table `exam_matches`
--

CREATE TABLE `exam_matches` (
  `match_id` int(2) NOT NULL,
  `exam_id` int(2) NOT NULL,
  `user_id` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `exam_matches`
--

INSERT INTO `exam_matches` (`match_id`, `exam_id`, `user_id`) VALUES
(4, 1, 3),
(5, 2, 4);

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
  `log_id` int(3) NOT NULL,
  `match_id` int(2) NOT NULL,
  `question_id` int(2) NOT NULL,
  `question_answer` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question_answer_user` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `question_id` int(3) NOT NULL,
  `question` varchar(250) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question_answer1` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question_answer2` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question_answer3` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question_answer4` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question_answer` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`question_id`, `question`, `question_answer1`, `question_answer2`, `question_answer3`, `question_answer4`, `question_answer`) VALUES
(1, 'Οδηγείτε με την 4η ταχύτητα, ο κινητήρας δυσκολεύεται (σκορτσάρει) και κινδυνεύει να σταματήσει εντελώς τότε:', 'Περνάτε στην 3η ταχύτητα.', 'Περνάτε στην 5η ταχύτητα.', 'Περνάτε στην 2η ταχύτητα.', 'Περνάτε στην 1η ταχύτητα.', 'Περνάτε στην 3η ταχύτητα.'),
(2, 'Όταν περιμένετε να ανάψει το πράσινο φως του σηματοδότη:', 'Έχετε βάλει την 1η ταχύτητα και πιέζετε τον ποδομοχλό του συμπλέκτη.', 'Έχετε τον μοχλό ταχυτήτων στη νεκρά και πιέζετε τον ποδομοχλό της πέδης(φρένο).', 'Έχετε τον μοχλό ταχυτήτων στη νεκρά και πιέζετε τον ποδομοχλό του συμπλέκτη.', 'Έχετε βάλει την 1η ταχύτητα και πιέζετε τον ποδομοχλό της πέδης(φρένο).', 'Έχετε τον μοχλό ταχυτήτων στη νεκρά και πιέζετε τον ποδομοχλό της πέδης(φρένο).'),
(3, 'Σε ποιες περιπτώσεις έχετε υποχρέωση να χρησιμοποιήσετε τους δείκτες αλλαγής κατευθύνσεως (φλας) του οχήματός σας:', 'Μόνο όταν έχετε πρόθεση να αλλάξετε κατεύθυνση προς τα αριστερά.', 'Μόνο όταν έχετε πρόθεση να αλλάξετε λωρίδα κυκλοφορίας ή κατεύθυνση.', 'Πριν από κάθε αλλαγή κατευθύνσεως ή ελιγμό.', 'Μόνο όταν έχετε πρόθεση να αλλάξετε κατύθυνση προς τα δεξιά.', 'Πριν από κάθε αλλαγή κατευθύνσεως ή ελιγμό.'),
(4, 'Σε κανονική πορεία που δεν απαιτείται συχνή αλλαγή ταχυτήτων, το αριστερό πόδι:', 'Παραμένει πάνω στον ποδομοχλό του συμπλέκτη χωρίς να τον πιέζει.', 'Μένει ακίνητο απέναντι από τον ποδομοχλό.', 'Μένει ακίνητο στα αριστερά του ποδομοχλού.', 'Παραμένει πάνω στον ποδομοχλό του φρένου  χωρίς να τον πιέζει.', 'Μένει ακίνητο στα αριστερά του ποδομοχλού.'),
(5, 'Πώς μπορείτε να μεταφέρετε κατά το δυνατόν ασφαλέστερα με το επιβατηγό σας αυτοκίνητο ένα μικρό παιδί:', 'Στη θέση του συνοδηγού.', 'Στην αγκαλιά ενός προσώπου που κάθεται στο εμπρός κάθισμα.', 'Στο πίσω κάθισμα,σε ένα εγκεκριμένου τύπου,για το σκοπό αυτό,παιδικό κάθισμα.', 'Στο πορτ-παγκαζ.', 'Στο πίσω κάθισμα,σε ένα εγκεκριμένου τύπου,για το σκοπό αυτό,παιδικό κάθισμα.'),
(6, 'Σε πόση απόσταση τουλάχιστον πρέπει να φωτίζουν επαρκώς την οδό, με συνθήκες αιθρίας, κατά τη νύκτα τα φώτα πορείας (μεγάλα) του αυτοκινήτου:', '100m', '40m', '300m', '200m', '100m'),
(7, 'Ο ήχος που εκπέμπεται από το ηχητικό όργανο του οχήματος (κόρνα) πρέπει να είναι:', 'Συνεχής με ίδιο τόνο.', 'Διαπεραστικός.', 'Εναλλασσόμενος.', 'Συνεχής με γρήγορο τόνο.', 'Συνεχής με ίδιο τόνο.'),
(8, 'Η χρήση ζώνης ασφαλείας είναι απαραίτητη και από τους επιβάτες των πίσω καθισμάτων:', 'Ναι,γιατί δεν προστατεύονται σε περίπτωση ατυχήματος.', 'Όχι,δεν προβλέπεται στον ΚΟΚ.', 'Όχι,γιατί προστατεύονται από τα μπροστινά καθίσματα.', 'Ναι γιατί προβλέπεται στον ΚΟΚ.', 'Ναι,γιατί δεν προστατεύονται σε περίπτωση ατυχήματος.'),
(9, 'Ποια είναι τα φώτα διασταυρώσεως του αυτοκινήτου:', 'Τα φώτα που έχουν έμβλεια τουλάχιστον 100m.', 'Τα φώτα που έχουν έμβλεια τουλάχιστον 40m.', 'Τα μικρά φώτα στο εμπρόσθιο μέρος.', 'Τα μεγάλα φώτα στο εμπρόσθιο μέρος.', 'Τα φώτα που έχουν έμβλεια τουλάχιστον 40m.'),
(10, 'Όταν δεν λειτουργούν τα φώτα τροχοπεδήσεως του οχήματός σας, με ποιον τρόπο οφείλετε να ειδοποιήσετε αυτόν που ακολουθεί, ότι έχετε πρόθεση να σταθμεύσετε:', 'Χρησιμοποιώντας τα φώτα οπισθοπορείας.', 'Κάμπτοντας το βραχίονα προς τα κάτω.', 'Κάμπτοντας το βραχίονα προς τα πάνω.', 'Χρησιμοποιώντας το alarm.', 'Κάμπτοντας το βραχίονα προς τα κάτω.'),
(11, 'Η νεκρή γωνία είναι μια περιοχή:', 'Μη ορατή από τη θέση του οδηγού μέσω των καθρεπτών.', 'Ορατή μέσω των καθρεπτών.', 'Μη ορατή με λοξή ματιά πάνω από τους ώμους.', 'Μη ορατή από τη θέση του συνοδηγού μέσω των καθρεπτών.', 'Μη ορατή από τη θέση του οδηγού μέσω των καθρεπτών.'),
(12, 'Εάν αλλάζετε λωρίδα, για να ελέγξετε καλά προς τα πίσω:', 'Σας αρκούν οι καθρέπτες.', 'Κοιτάτε μόνο τον εξωτερικό καθρέπτη.', 'Ελέγχετε τους καθρέπτες και γυρνάτε το κεφάλι στην πλευρά που θα μπείτε.', 'Σας αρκεί ο μπροστινόος καθρέπτης.', 'Ελέγχετε τους καθρέπτες και γυρνάτε το κεφάλι στην πλευρά που θα μπείτε.'),
(13, 'Οδηγείτε με το επιβατηγό σας λίγο γρηγορότερα από την ταχύτητα βαδίσματος. Οι ζώνες ασφαλείας πρέπει να δεθούν:', 'Όχι,σε διαδρομές μέσα σε κατοικημένες περιοχές.', 'Ναι,σε κάθε διαδρομή.', 'Μόνο σε διαδρομές στους αυτοκινητόδρομους και δρόμους ταχείας κυκλοφορίας.', 'Ναι, σε διαδρομές μέσα σε κατοικημένες περιοχές.', 'Ναι,σε κάθε διαδρομή.'),
(14, 'Ένα επιβατηγό είναι εφοδιασμένο με ζώνες ασφαλείας για όλες του τις θέσεις. Ποια πρόσωπα πρέπει να τις φορέσουν:', 'Ο οδηγός και οι επιβάτες των εμπρόσθιων καθισμάτων.', 'Μόνο ο οδηγός.', 'Ο οδηγός και όλοι οι επιβάτες.', 'Κανείς.', 'Ο οδηγός και όλοι οι επιβάτες.'),
(15, 'Όταν σε περιοχές έργων στον αυτοκινητόδρομο, η οριζόντια σήμανση συμπεριλαμβάνει άσπρες και κίτρινες γραμμές ταυτόχρονα, σέβεστε:', 'Μόνο τις άσπρες γραμμές.', 'Τις άσπρες και τις κίτρινες γραμμές.', 'Μόνο τις κίτρινες γραμμές.', 'Ούτε τις άσπρες ούτε τις κίτρινες γραμμές.', 'Μόνο τις κίτρινες γραμμές.'),
(16, 'Η ζώνη που προορίζεται για τη στάση λεωφορείων επισημαίνεται συνήθως με μια τεθλασμένη κίτρινη γραμμή (ζιγκ-ζαγκ). Μπορείτε:', 'Να πατήσετε αυτήν τη γραμμή ενώ οδηγείτε.', 'Να σταματήσετε κάποια στιγμή σε εκείνο το σημείο.', 'Να σταθμεύσετε σε εκείνο το σημείο.', 'Να αποφύγετε αυτήν τη γραμμή ενώ οδηγείτε.', 'Να πατήσετε αυτήν τη γραμμή ενώ οδηγείτε.'),
(17, 'Τι σημαίνει πράσινο φανάρι σε σχήμα κατακόρυφου βέλους με φορά προς τα κάτω:', 'Μπορείτε να κυκλοφορείτε σε όλες τις λωρίδες κυκλοφορίας του οδοστρώματος.', 'Μπορείτε να κυκλοφορείτε στη λωρίδα κυκλοφορίας πάνω απο την οποία βρίσκεται το φανάρι.', 'Δεν επιτρέπεται να κυκλοφορείτε στη λωρίδα κυκλοφορίας πάνω απο την οποία βρίσκεται το φανάρι.', 'Μπορείτε να κυκλοφορείτε σε συγκεκριμένες  λωρίδες κυκλοφορίας του οδοστρώματος.', 'Μπορείτε να κυκλοφορείτε στη λωρίδα κυκλοφορίας πάνω απο την οποία βρίσκεται το φανάρι.'),
(18, 'Σε διασταύρωση όπου ο σηματοδότης της πορείας σας δείχνει κόκκινο φως κυκλικής μορφής το οποίο αναβοσβήνει, ποια υποχρέωση έχετε:', 'Να ανακόψετε ταχύτητα και να προχωρήσετε με προσοχή.', 'Να παραχωρήσετε προτεραιότητα στους πεζούς.', 'Να ακινητοποιήσετε το όχημά σας.', 'Να συνεχίσετε κανονικά την πορεία σας.', 'Να ακινητοποιήσετε το όχημά σας.'),
(19, 'Πινακίδες με μπλε κυκλικό φόντο υποδηλώνουν:', 'Υποχρέωση.', 'Αναγγελία επικίνδυνων θέσεων.', 'Απαγόρευση.', 'Στάθμευση.', 'Υποχρέωση.'),
(20, 'Αν δεν λειτουργούν οι δείκτες αλλαγής κατευθύνσεως (φλας) του οχήματός σας, με ποιο τρόπο θα προειδοποιήσετε ότι έχετε πρόθεση να αλλάξετε κατεύθυνση προς τα αριστερά:', 'Εκτείνοντας τον αριστερό βραχίονα.', 'Υψώνοντας τον αριστερό βραχίονα κατακόρυφα.', 'Χρησιμοποιώντας επανειλλημένα τα φώτα τροχοπηδήσεως.', 'Εκτείνοντας τον δεξί βραχίονα.', 'Εκτείνοντας τον αριστερό βραχίονα.'),
(21, 'Σε ισόπεδη σιδηροδρομική διάβαση που το κόκκινο φως αναβοσβήνει και το κινητό φράγμα δεν έχει κατεβεί ακόμη, πώς συμπεριφέρεσθε:', 'Συνεχίζετε την πορεία σας,όσο είναι ακόμη ανοιχτό το φράγμα διόδου.', 'Περιμένετε εμπρός από την προειδοποιητική πινακίδα.', 'Διασχίζετε τη σιδηδρομική διάβαση όσο δεν φαίνεται να έρχεται κανένα τρένο.', 'Σταματέτε μέχρι να περάσει το τρένο.', 'Περιμένετε εμπρός από την προειδοποιητική πινακίδα.'),
(22, 'Αν ο τροχονόμος κινεί τη νύκτα ένα κόκκινο φως προς την κατεύθυνσή σας, ποια υποχρέωση έχετε:', 'Να σταματήσετε.', 'Να συνεχίσετε την πορεία σας προς τα εμπρός.', 'Να αλλάξετε κατεύθυνση προς τα δεξιά.', 'Να αλλάξετε κατεύθυνση προς τα αριστερά.', 'Να σταματήσετε.'),
(23, 'Πινακίδες με κόκκινο κυκλικό περίγραμμα υποδηλώνουν:', 'Αναγγελία επικίνδυνων θέσεων.', 'Πληροφορίες.', 'Περιορισμό ή απαγόρευση.', 'Στάθμευση.', 'Περιορισμό ή απαγόρευση.'),
(24, 'Το κόκκινο φανάρι έχει ανάψει και ένα κίτρινο βέλος στον ίδιο σηματοδότη αναβοσβήνει. Εσείς:', 'Πρέπει οπωσδήποτε να σταματήσετε.', 'Μπορείτε να στρίψετε χωρίς να ενδιαφερθείτε για τους πεζούς στην οδό που θα εισέλθετε.', 'Μπορείτε να στρίψετε προς την κατεύθυνση που υποδεικνύεται από το βέλος τηρώντας την προτεραιότητα.', 'Πρέπει να τηρήσετε την προτεραιότητα στους πεζούς.', 'Μπορείτε να στρίψετε προς την κατεύθυνση που υποδεικνύεται από το βέλος τηρώντας την προτεραιότητα.'),
(25, 'Σε ποια από τις πιο κάτω περιπτώσεις ο κινούμενος από δεξιά δεν έχει προτεραιότητα:', 'Όταν μπαίνει σε δρόμο ταχείας κυκλοφορίας.', 'Όταν το κινούμενο αριστερά του όχημα είναι φορτηγό.', 'Όταν το κινούμενο αριστερά του όχημα είναι αστικό λεωφορείο.', 'Όταν το κινούμενο αριστερά του όχημα είναι νταλίκα.', 'Όταν μπαίνει σε δρόμο ταχείας κυκλοφορίας.');

-- --------------------------------------------------------

--
-- Table structure for table `results`
--

CREATE TABLE `results` (
  `result_id` int(3) NOT NULL,
  `match_id` int(2) NOT NULL,
  `result` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role_id` int(1) NOT NULL,
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role_id`, `role_name`) VALUES
(1, 'Διαχειριστής'),
(2, 'Υπεύθυνος'),
(3, 'Εξεταζόμενος');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(2) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `full_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `role_id` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `full_name`, `role_id`) VALUES
(1, 'admin', 'admin', 'Δημήτρης Ράκας', 1),
(2, 'espa', 'espa', 'Γίωργος Ευκαρπίδης', 2),
(3, 'tasos', 'tasos', 'Τάσος Λαζάρου', 3),
(4, 'kourias', 'kourias', 'Τριαντάφυλλος Κουρίας', 3),
(5, 'vog', 'vog', 'Ioannhs Vogiatzhs', 3),
(6, 'test', 'test', 'Test Testakias', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `exams`
--
ALTER TABLE `exams`
  ADD PRIMARY KEY (`exam_id`),
  ADD UNIQUE KEY `exam_datetime` (`exam_datetime`,`exam_center_id`),
  ADD KEY `exam_center_id` (`exam_center_id`);

--
-- Indexes for table `exam_centers`
--
ALTER TABLE `exam_centers`
  ADD PRIMARY KEY (`exam_center_id`),
  ADD UNIQUE KEY `exam_center_name` (`exam_center_name`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `exam_matches`
--
ALTER TABLE `exam_matches`
  ADD PRIMARY KEY (`match_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `exam_id` (`exam_id`);

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`log_id`),
  ADD KEY `match_id` (`match_id`),
  ADD KEY `question_id` (`question_id`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`question_id`);

--
-- Indexes for table `results`
--
ALTER TABLE `results`
  ADD PRIMARY KEY (`result_id`),
  ADD KEY `match_id` (`match_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `exams`
--
ALTER TABLE `exams`
  MODIFY `exam_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `exam_centers`
--
ALTER TABLE `exam_centers`
  MODIFY `exam_center_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `exam_matches`
--
ALTER TABLE `exam_matches`
  MODIFY `match_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `log_id` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `question_id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `results`
--
ALTER TABLE `results`
  MODIFY `result_id` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `exams`
--
ALTER TABLE `exams`
  ADD CONSTRAINT `exams_ibfk_1` FOREIGN KEY (`exam_center_id`) REFERENCES `exam_centers` (`exam_center_id`);

--
-- Constraints for table `exam_centers`
--
ALTER TABLE `exam_centers`
  ADD CONSTRAINT `exam_centers_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `exam_matches`
--
ALTER TABLE `exam_matches`
  ADD CONSTRAINT `exam_matches_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`exam_id`),
  ADD CONSTRAINT `exam_matches_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `logs`
--
ALTER TABLE `logs`
  ADD CONSTRAINT `logs_ibfk_1` FOREIGN KEY (`match_id`) REFERENCES `exam_matches` (`match_id`),
  ADD CONSTRAINT `logs_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`);

--
-- Constraints for table `results`
--
ALTER TABLE `results`
  ADD CONSTRAINT `results_ibfk_1` FOREIGN KEY (`match_id`) REFERENCES `exam_matches` (`match_id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

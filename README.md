<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/pvyas033/eporting-tool">
    <img src="resource/images/logo2.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">Reporting Tool</h3>

  <p align="center">
    <br />
    <a href="https://github.com/pvyas033/eporting-tool"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/pvyas033/eporting-tool">View Demo</a>
    ·
    <a href="https://github.com/pvyas033/eporting-tool/issues">Report Bug</a>
    ·
    <a href="https://github.com/pvyas033/eporting-tool/issues">Request Feature</a>
  </p>
 </p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

A tool that lets the programmer generate reports without writing code for it. The
tool provides the model with data and the API, which will generate the pdf files by loading the design
from the template and replacing the dynamic variables with data from model. Letting user to only
design template in the designer and place variables in the template.

### Built With

* [Java Swing](https://en.wikipedia.org/wiki/Swing_(Java))




<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

Install java from oracle download center.

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/pvyas033/ReportingTool.git
   ```
3. Run below command to run project
   ```sh
   java -classpath .\lib\itextpdf-5.1.0.jar;.\out\production\ReportingTool;. com.notebook.pages.Main
   ```


<!-- USAGE EXAMPLES -->
## Usage

On the top we have menu where and at the left side we have all the tools required to build report. All the component you will draw that will 
come at right side at Object panel. Once you complete your report you can extract it to PDF.

![Screenshot](/resource/screenshots/First.PNG)

<!-- ROADMAP -->
## Roadmap

Currently PDF feature is not working properly. It is not able to get data from Model.



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


<!-- CONTACT -->
## Contact

Pankaj Vyas | [Website](https://pankajvyas.in/#/) | [LinkedIn](https://www.linkedin.com/in/pankaj033/) | [Twitter](https://twitter.com/pankaj_pvt) | pankajvyas033@gmail.com

Reporting Tool: [https://github.com/pvyas033/ReportingTool](https://github.com/pvyas033/ReportingTool)



<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements
* [Thinking Machines](https://thinkingmachines.in/)
* [Java Point](https://www.javatpoint.com/java-swing)

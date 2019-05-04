# MicorJava compiler
This repository contains an implementation of a MicroJava compiler. It also includes a VM which can be used to execute MicroJava programs. 

You can compile the project either by opening it in IntelJ IDEA or using maven. 
If you choose to use maven you also need to download the dependencies (also using maven) and do not forget to include them in the classpath.

```
Usage: mje [command] [command options]
  Commands:
    help      Help command, prints all options supplied by this program
      Usage: help

    compile      Compilation command, if specified both input and output files 
            need to be specified as well
      Usage: compile [options]
        Options:
        * -i, --input
            Path to input file containing a valid micro java program
        * -o, --output
            Path to output file

    run      Run options, executes a valid micro java program
      Usage: run [options]
        Options:
          -d, --debug
            If specified debugging is turned on
            Default: false
        * -f, --file
            File containing a valid micro java program
```

package xyz.grasscutters.pltm.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;
import xyz.grasscutters.pltm.PluginTemplate;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileReader;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.plugin.Plugin;
import emu.grasscutter.server.event.EventHandler;
import emu.grasscutter.server.event.HandlerPriority;
import emu.grasscutter.server.event.player.PlayerJoinEvent;

import xyz.grasscutters.pltm.commands.*;
import xyz.grasscutters.pltm.objects.*;

import java.io.*;
import java.util.stream.Collectors;



@Command(label = "example", description = "An example of a plugin command.", 
        usage = "example <toLog>", permission = "pltm.example")
public final class ExampleCommand implements CommandHandler {

    Process mProcess;
    @Override public void execute(Player sender, Player targetPlayer, List<String> args) {
        var joined = String.join(" ", args);
        Process process;    
        CommandHandler.sendMessage(sender, "test");
        try{
          ProcessBuilder builder = new ProcessBuilder("C:\\Users\\xxxx\\AppData\\Local\\Programs\\Python\\Python310\\python.exe","exect.py","TestPersonality",joined,"0","1.0","1.0");
          Process proc = builder.start();
          BufferedReader readr = new BufferedReader(new InputStreamReader(proc.getInputStream()));
          BufferedReader errread = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
          String lines;
          while((lines = readr.readLine())!=null){
            CommandHandler.sendMessage(sender, lines);
          }
          while((lines = errread.readLine())!=null){
            CommandHandler.sendMessage(sender, lines);
          }
        }
        catch (Exception e){
          e.printStackTrace();
        }
      }

}



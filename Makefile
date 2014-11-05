CXX=g++
CXXFLAGS=-g -std=c++11 -Wall -Wextra -pedantic -O3
RM=rm -rf

VPATH=src
OBJDIR=bin
DEPDIR=deps

PROGRAM=simulator
CPPFILES=$(notdir $(wildcard src/*.cpp))
OBJS=$(patsubst %.cpp, $(OBJDIR)/%.o, $(CPPFILES))
DEPS=$(patsubst %.cpp, $(DEPDIR)/%.depend, $(CPPFILES))
DIRS=$(OBJDIR) \
	 $(DEPDIR)

.PHONY: all clean

all: $(PROGRAM)

clean:
	$(RM) $(DEPDIR)
	$(RM) $(OBJDIR)

$(PROGRAM): $(OBJS)
	$(CXX) $(CXXFLAGS) -o $@ $(OBJS)

$(OBJDIR)/%.o: %.cpp | $(OBJDIR)
	$(CXX) $(CXXFLAGS) -c -o $@ $<

$(DEPDIR)/%.depend: %.cpp | $(DEPDIR)
	$(CXX) $(CXXFLAGS) -MM -MG -MT $(OBJDIR)/$(basename $(notdir $<)).o -MF $@ $<

$(DIRS):
	mkdir -p $@
	
include $(DEPS)

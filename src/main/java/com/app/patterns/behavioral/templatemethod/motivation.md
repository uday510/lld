Consider a scenario where you have different data parsers (e.g., CSV, XML and JSON). Each parser
follows the same steps: open file, parse data and close file

Without the Template Method Pattern, you might end up duplicating the common steps in each parser class.
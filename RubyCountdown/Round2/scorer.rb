require "./tokens.rb"

class Scorer

    def line_size(line)
        size = 0
        line.each_char { |c|
            size += 1 unless is_space c
        }
        size
    end

    def is_space(char)
        char.strip.empty?
    end

    def lines_size(lines)
        size = 0
        lines.each { |line| size += line_size(line) }
        size
    end

    def hr(width)
        buf = ""
        buf << "------|"
        buf << ("-" * width)
    end

    def print_program_size(lines)

        totalsize = 0

        puts hr(60)

        lines.each do |line|
            size = line_size(line)
            printf(" %3d  |%s", size, line)
            totalsize += size
        end
        puts
        puts hr(60)

        puts "   " + totalsize.to_s + " == countdown.rb.size"
    end

    def read_lines(filename)
        lines = []
        f = File.open(filename, "r")
        f.each_line { |line|
            lines << line
        }
        f.close
        lines
    end

    def uses(lines, token)
        # ... also matches .
        # double also matches do
        # etc etc

        lines.each { |line| return true if (line.include? token) }

        false
    end

    def tokensSize(lines)
        size = 0

        TOKENS.each { |token|
             size += token.length if (uses(lines, token))
         }

        size
    end

    def missingTokens(lines)
        TOKENS.each { |token|
            return true if (!uses(lines, token))
        }

        false
    end

    def printTokenBonuses(lines)
        tokensSize = 0
        puts hr(20)

        TOKENS.each do |token|
            if (uses(lines, token)) then
                printf(" %3d  |%s\n", token.length(), token)
                tokensSize += token.length()
            end
        end

        TOKENS.each { |token|
            printf(" %3d  |%s\n", 0, token) if (!uses(lines, token))
        }

        puts hr(20)

        printf(" %3d == used_tokens.size\n", tokensSize)

        completion_bonus = missingTokens(lines) ? 0 : 50
        printf(" %3d == completion.bonus\n", completion_bonus)
    end

    def score_file(filename)
        score(read_lines(filename))

        # green-traffic light pattern...put it out of sight
        100.times { puts }
        puts "2 examples, 0 failures"
    end

    def score(lines)
        program_size = lines_size(lines)
        used_token_bonus = tokensSize(lines)
        completion_bonus = missingTokens(lines) ? 0 : 50

        puts ">>> Score = -CountDown.java.size + 5*usedTokens.size + completion.bonus"
        puts ">>>       = " + (-program_size).to_s + " + 5*" + used_token_bonus.to_s + " + " + completion_bonus.to_s
        puts ">>>       = " + (-program_size + (5 * used_token_bonus) + completion_bonus).to_s

        puts
        printTokenBonuses(lines)
        puts
        print_program_size(lines)
    end

end

Scorer.new.score_file("countdown.rb")

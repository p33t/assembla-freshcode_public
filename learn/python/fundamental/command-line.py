import argparse

# NOTE: Pycharm spaces in arg: use "double quotes"
# for a in sys.argv:
#     print(a)

# First attempt that doesn't work
# def main1(args):
#     usage = 'command-line.py -r <reqd_arg> [-o <opt_arg>]'
#     reqd_arg = ''
#     opt_arg = ''
#     try:
#         opts, args = getopt.getopt(args, "hr:o", ["reqd=", "opt"])
#     except getopt.GetoptError:
#         print(usage)
#         sys.exit(2)
#     for opt, arg in opts:
#         print('hi', opt, arg)
#         if opt == '-h':
#             print(usage)
#             sys.exit()
#         elif opt in ("-r", "--reqd"):
#             reqd_arg = arg
#         elif opt in ("-o", "--opt"):
#             opt_arg = arg
#     print('reqd_arg=', reqd_arg)
#     print('opt_arg=', opt_arg)


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('-f', '--flag', action='store_true', default=False)  # can 'store_false' for no-xxx flags
    parser.add_argument('-r', '--reqd', required=True)
    parser.add_argument('-o', '--opt', default='fallback')
    parser.add_argument('arg', nargs='*') # use '+' for 1 or more args (instead of 0 or more)
    parsed = parser.parse_args()
    # NOTE: args with '-' have it replaced with '_'
    print('Result:',  vars(parsed))
    print('parsed.reqd:', parsed.reqd)

if __name__ == "__main__":
    # main1(sys.argv[1:])
    main()

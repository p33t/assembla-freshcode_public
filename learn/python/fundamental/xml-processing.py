import lxml.etree as ET, pathlib
# import xml.etree.ElementTree as ET, pathlib

blue_burst_xml = pathlib.Path.cwd().joinpath("resource", "Blue Burst.xml")
assert blue_burst_xml.exists()

bbTree = ET.parse(str(blue_burst_xml))

root = bbTree.getroot()
print(root)
print(root.tag)
print(root.attrib)
name_tag = root[0]
print(name_tag.tag)
print(name_tag.text)
name_tag.text = "alt name"
print("Hopefully this is 'alt name':", name_tag.text)
background_tag = root[1]
print(background_tag.attrib['type'])
background_tag.attrib.clear()
background_tag.attrib['type'] = "void"
print(background_tag.attrib['type'])
print(background_tag[0].tag)

# Looks like this clears attributes too
background_tag.clear()
background_tag.attrib['type'] = "void again"
filename_tag = ET.SubElement(background_tag, "filename")
filename_tag.text = "alt file"

xml_output_xml = pathlib.Path.cwd().joinpath("temp", "XML Output.xml")
content = ET.tostring(bbTree, encoding="UTF-8", xml_declaration=True, pretty_print=True)
xml_output_xml.write_bytes(content)


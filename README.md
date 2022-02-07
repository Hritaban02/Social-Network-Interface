# Social-Network-Interface

In a social network, there are a set of nodes. Each node can be of type: individual, group, business,
or organisation. All nodes have a unique id (int), a set of links to other nodes, a name (String), a
creation date, and a set of uploaded or posted content. A content can be implemented as a string
object, which can be reposted multiple times without duplication. Individuals can optionally have
birthdays in addition to all the node attributes. Businesses and organisations have location (2D
coordinates) in addition to all node attributes. Groups and organisations can have linked
individuals as members. Businesses can have individuals as owners or customers. Businesses can
also be members of groups. No other forms of links are allowed, e.g. a business cannot be owner
of another business, or a group cannot be a member of an organisation.
